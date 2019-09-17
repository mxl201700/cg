package cn.itcast.util;

import cn.itcast.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Package: cn.itcast.util
 * Author: Mxl
 * Date: Created in 2019/8/19 10:34
 **/
public class FastDFSClient {

    static {
        //从classpath获取文件路径
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //图片上传

    public static String[] upload(FastDFSFile file){

        try {
            //创建一个trackerclient客户端
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //5.创建stroageserver 对象
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            //参数1 字节数组
            //参数2 扩展名(不带点)
            //参数3 元数据( 文件的大小,文件的作者,文件的创建时间戳)


            NameValuePair[] meta_list = new NameValuePair[]{new NameValuePair(file.getAuthor()),new NameValuePair(file.getName())};
            String[] strings = storageClient.upload_file(file.getContent(), file.getExt(),meta_list);

            return strings;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }


    //图片下载
    public static InputStream downFile(String groupName, String remoteFileName){

        ByteArrayInputStream byteArrayInputStream = null;
        try {
            //创建一个trackerclient客户端
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建storageServer对象
            StorageServer storageServer = null;
            //创建storageclient对象
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            //7.根据组名 和 文件名 下载图片

            //参数1:指定组名
            //参数2 :指定远程的文件名
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            return byteArrayInputStream;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayInputStream!=null){
                    byteArrayInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //图片删除

    public static void deleteFile(String groupName, String remoteFileName){

        try {
            //创建一个trackerclient客户端
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建storageServer对象
            StorageServer storageServer = null;
            //创建storageclient对象
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);

            //7.删除图片 (根据组名 和文件名来删除)

            int group1 = storageClient.delete_file(groupName, remoteFileName);

            if (group1==0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据组名获取组的信息

    public static StorageServer getStorages(String groupName){
        try {
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();

            //参数1 指定traqckerserver 对象
            //参数2 指定组名
            StorageServer group1 = trackerClient.getStoreStorage(trackerServer, groupName);
            return group1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据文件名和组名获取文件的信息

    public static FileInfo getFile(String groupName, String remoteFileName){

        try {
            //创建一个trackerclient客户端
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建storageServer对象
            StorageServer storageServer = null;
            //创建storageclient对象
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);

            //参数1 指定组名
            //参数2 指定文件的路径
            FileInfo group1 = storageClient.get_file_info(groupName, remoteFileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取tracker 的ip和端口的信息
    //http://192.168.211.132:8080
    public static String getTrackerUrl(){
        try {
            //创建一个trackerclient客户端
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            String hostString = trackerServer.getInetSocketAddress().getHostString();

            int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
            return "http://" + hostString + ":" + g_tracker_http_port;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
