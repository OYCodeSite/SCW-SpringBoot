package com.oy.scw.project.component;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @Author OY
 * @Date 2021/2/14
 */
@ToString
@Data
@Slf4j
public class OssTemplate {

    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    String bucketName;
    String objectName;

    public String upload(String filename, InputStream inputStream){

        log.debug("endpoint={}",endpoint);
        log.debug("accessKeyId={}",accessKeyId);
        log.debug("accessKeySecret={}",accessKeySecret);
        log.debug("bucket={}",bucketName);

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
            // 上传文件流。
//        InputStream inputStream = new FileInputStream("E:/imgs/1.jpeg");
            ossClient.putObject(bucketName, objectName+"/"+filename, inputStream);

            // https://oypicbed.oss-cn-shenzhen.aliyuncs.com/pic/p1.jpg
            String filepath = "https://"+bucketName+"."+endpoint+"/"+objectName+"/"+filename;
            // 关闭OSSClient。
            ossClient.shutdown();
            log.debug("文件上传成功-{}",filepath);

            return filepath;
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("文件上传失败-{}",filename);
            return null;
        }
    }
}
