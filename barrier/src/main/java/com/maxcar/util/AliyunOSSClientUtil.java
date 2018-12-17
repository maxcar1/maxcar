package com.maxcar.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;

/**
 * 图片上传至阿里云oss精简版
 * yangshujun
 */
public class AliyunOSSClientUtil {

    /**
     * 阿里云上传图片返回url
     * @param endpoint
     * @param accessKeyId
     * @param bucketName
     * @param accessKeySecret
     * @param objectKey
     * @param fileUrl
     * @return
     */
    public static String uploadOss(String endpoint,String accessKeyId,String bucketName,
                                          String accessKeySecret,String objectKey,String fileUrl){
        OSS ossClient = null ;
        String imageUrl = "";
        try {
            FileInputStream input = new FileInputStream(fileUrl) ;
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            File file = new File(objectKey);
            ossClient.putObject(bucketName,file.getPath(),input);
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            // 生成URL
            URL url = ossClient.generatePresignedUrl(bucketName, file.getPath() , expiration);
            imageUrl = url.toString().split("\\?")[0];
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (null != ossClient){
                ossClient.shutdown();
            }
        }
        return  imageUrl;
    }
}
