package com.neilren.neilren4j.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.neilren.neilren4j.common.config.Global;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * 阿里云 OSS 上传实现 By RenFei
 */
public class AliyunOssUtil {
    private static Logger log = Logger.getLogger(AliyunOssUtil.class);
    private static String endpoint = Global.getConfig("aliyun.oss.endpoint");
    private static String accessKeyId = Global.getConfig("aliyun.accessKeyId");
    private static String accessKeySecret = Global.getConfig("aliyun.accessKeySecret");
    private static String bucketName = Global.getConfig("aliyun.oss.bucketName");

    public static boolean upLoad(File file, String fileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            //确定bucket是否存在
            if (!ossClient.doesBucketExist(bucketName)) {
                //不存在，创建bucket
                System.out.println("Creating bucket " + bucketName + "\n");
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //上传对象
            ossClient.putObject(new PutObjectRequest(bucketName, fileName, file));
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            ossClient.shutdown();
        }
    }

    public static String upLoad(MultipartFile multipartFile) {
        try {
            CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File file = fi.getStoreLocation();
            //获得文件后缀名
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String fileName = "image/album/" + System.currentTimeMillis() + (Math.random() * 100) + suffix;
            upLoad(file, fileName);
            return Global.getConfig("aliyun.oss.domain") + "/" + fileName;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
