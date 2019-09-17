package cn.itcast.test;

import org.csource.fastdfs.*;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Package: cn.itcast.test
 * Author: Mxl
 * Date: Created in 2019/8/19 20:16
 **/

public class FastdfsTest {

    //上传
    @Test
    public void upload() throws Exception{
        //创建配置文件

        //加载配置文件

        //指定配置文件路径
        ClientGlobal.init("D:\\ideaws2\\changgouparent\\itcast-service\\itcast-service-file\\src\\main\\resources\\fdfs_client.conf");

        //创建一个trackerclient客户端
        TrackerClient trackerClient = new TrackerClient();

        //4.创建trackerserver 对象
        TrackerServer trackerServer = trackerClient.getConnection();

        //5.创建stroageserver 对象

        StorageServer storageServer = null;

        //6.创建storageclient 对象
        //参数1:trackerserver的对象
        //参数2 :storageserver对象

        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

        //7.上传图片(使用stroageclient api 实现)
        //参数1 :指定本地文件路径
        //参数2 :指定文件的扩展名 不要带点
        //参数3 :元数据(文件的大小,文件时间错,文件的作者,像素.......) 可以为空
        String[] jpgs = storageClient.upload_file("E:\\相册\\DCIM\\tassistant\\9.jpg", "jpg", null);

        for (String jpg : jpgs) {
            System.out.println(jpg);
        }
    }


    //下载
    @Test
    public void download() throws Exception{

        //1.创建一个配置文件 (配置服务器的地址 ,端口,连接的信息)
        //2.加载配置文件
        ClientGlobal.init("D:\\ideaws2\\changgouparent\\itcast-service\\itcast-service-file\\src\\main\\resources\\fdfs_client.conf");
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
        byte[] bytes = storageClient.download_file("group1", "M00/00/00/wKjThF1al66AWPdjAAIIm7S6TGQ324.jpg");

        //写流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/12345678.jpg"));
        fileOutputStream.write(bytes);

        fileOutputStream.close();
    }

    //删除
    @Test
    public void delete() throws Exception{
        //1.创建一个配置文件 (配置服务器的地址 ,端口,连接的信息)
        //2.加载配置文件
        ClientGlobal.init("D:\\ideaws2\\changgouparent\\itcast-service\\itcast-service-file\\src\\main\\resources\\fdfs_client.conf");
        //创建一个trackerclient客户端
        TrackerClient trackerClient = new TrackerClient();
        //4.创建trackerserver 对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建storageServer对象
        StorageServer storageServer = null;
        //创建storageclient对象
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

        //7.删除图片 (根据组名 和文件名来删除)

        int group1 = storageClient.delete_file("group1", "M00/00/00/wKjThF1al66AWPdjAAIIm7S6TGQ324.jpg");

        if (group1==0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    //根据组名 和文件名获取文件的信息
    @Test
    public void getFileinfo() throws Exception{
        //1.创建一个配置文件 (配置服务器的地址 ,端口,连接的信息)
        //2.加载配置文件
        ClientGlobal.init("D:\\ideaws2\\changgouparent\\itcast-service\\itcast-service-file\\src\\main\\resources\\fdfs_client.conf");
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
        FileInfo group1 = storageClient.get_file_info("group1", "M00/00/00/wKjThF1ansuAPLzPAAIIm7S6TGQ024.jpg");
        System.out.println(group1.getSourceIpAddr()+":"+group1.getFileSize());
    }

    //根据组名称 获取该组对应的组服务信息
    @Test
    public void getStroageServerInfo() throws Exception{
        //1.创建一个配置文件 (配置服务器的地址 ,端口,连接的信息)
        //2.加载配置文件
        ClientGlobal.init("D:\\ideaws2\\changgouparent\\itcast-service\\itcast-service-file\\src\\main\\resources\\fdfs_client.conf");
        //创建一个trackerclient客户端
        TrackerClient trackerClient = new TrackerClient();
        //4.创建trackerserver 对象
        TrackerServer trackerServer = trackerClient.getConnection();

        //参数1 指定traqckerserver 对象
        //参数2 指定组名
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer, "group1");
        System.out.println(storeStorage.getInetSocketAddress().getHostString()+"+:"+storeStorage.getInetSocketAddress().getPort());

    }

    //根据文件名和组名和tracker 获取该文件所在的storage的服务器的数组信息
    @Test
    public void getserverinfo() throws Exception{

        //1.创建一个配置文件 (配置服务器的地址 ,端口,连接的信息)
        //2.加载配置文件
        ClientGlobal.init("D:\\ideaws2\\changgouparent\\itcast-service\\itcast-service-file\\src\\main\\resources\\fdfs_client.conf");
        //创建一个trackerclient客户端
        TrackerClient trackerClient = new TrackerClient();
        //4.创建trackerserver 对象
        TrackerServer trackerServer = trackerClient.getConnection();

        ServerInfo[] group1s = trackerClient.getFetchStorages(trackerServer, "group1", "M00/00/00/wKjThF1ansuAPLzPAAIIm7S6TGQ024.jpg");
        for (ServerInfo group1 : group1s) {
            System.out.println(group1.getIpAddr()+";"+group1.getPort());
        }
    }

    //获取tracker的地址(ip 和端口)
    @Test
    public void getTrackerInfo() throws Exception{
        //1.创建一个配置文件 (配置服务器的地址 ,端口,连接的信息)
        //2.加载配置文件
        ClientGlobal.init("D:\\ideaws2\\changgouparent\\itcast-service\\itcast-service-file\\src\\main\\resources\\fdfs_client.conf");
        //创建一个trackerclient客户端
        TrackerClient trackerClient = new TrackerClient();
        //4.创建trackerserver 对象
        TrackerServer trackerServer = trackerClient.getConnection();
        String hostString = trackerServer.getInetSocketAddress().getHostString();

        int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
        System.out.println("http://"+hostString+":"+g_tracker_http_port);
    }


}
