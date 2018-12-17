package com.maxcar.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutImageStyleRequest;

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
            // 缩放
            String style = "image/resize,m_fixed,w_100,h_100";
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectKey);
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            request.setProcess(style);
            request.setExpiration(expiration);
            // 生成URL
            URL url = ossClient.generatePresignedUrl(request);
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

    /**
     * 阿里云上传文件精简版
     * @param endpoint
     * @param accessKeyId
     * @param bucketName
     * @param accessKeySecret
     * @param objectKey
     * @return
     */
    public static String uploadSimpleFile(String endpoint,String accessKeyId,String bucketName,
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
            ossClient.shutdown();
        }
        return  imageUrl;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAIG2Gjf74uxcYN";
        String bucketName = "maxcar-pic";
        String accessKeySecret = "7Iqd0TDHEQuvEQWB8oXif2hj3nAoui";
        String objectKey = "data/parking/image/1.jpg";
        String fileUrl = "D:\\data\\parking\\image\\20181207\\20181207100823.jpg";
        String url = uploadOss(endpoint,accessKeyId,bucketName,accessKeySecret,fileUrl,fileUrl);
        System.out.println("耗时"+String.valueOf((System.currentTimeMillis()-start)/1000)+"s");
        System.out.println(url);
    }
}
