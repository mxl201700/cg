package cn.itcast.file.controller;

import cn.itcast.file.FastDFSFile;
import cn.itcast.util.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Package: cn.itcast.file.controller
 * Author: Mxl
 * Date: Created in 2019/8/19 21:58
 **/
@RestController
@CrossOrigin
public class FileController {

    /***
     * 文件上传
     * @return
     */
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) throws Exception{

        //1. 创建图片文件对象(封装)
        //截取
        //String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

        FastDFSFile fastDFSFile = new FastDFSFile(
            file.getOriginalFilename(),//原来的文件名
            file.getBytes(),//文件本身的字节数组
            StringUtils.getFilenameExtension( file.getOriginalFilename()));
        //2. 调用工具类实现图片上传
        String[] upload = FastDFSClient.upload(fastDFSFile);
        //3. 拼接图片的全路径返回
        //  upload[0] group1
        //  upload[1] M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg
        return FastDFSClient.getTrackerUrl()+"/"+upload[0]+"/"+upload[1];
    }
}
