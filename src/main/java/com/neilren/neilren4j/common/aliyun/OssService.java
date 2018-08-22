package com.neilren.neilren4j.common.aliyun;

import com.aliyun.oss.OSSClient;
import com.neilren.neilren4j.common.conf.Neilren4jConfig;
import com.neilren.neilren4j.service.interfaces.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName OssService
 * @Description 阿里云的OSS服务实现文件上传功能
 * @Date 2018/7/31 14:59
 */
@Slf4j
@Service
public class OssService implements UploadService {
    @Autowired
    private Neilren4jConfig neilren4jConfig;

    public String upload(File file) {
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String filename = UUID.randomUUID().toString().replace("-", "").toLowerCase() + "." + suffix;
        String path = doUpload(neilren4jConfig.getAliyun().getOss().getRootPath() + filename, file);
        return neilren4jConfig.getAliyun().getCdn().getHost() + path;
    }

    private String doUpload(String objectPath, File file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = neilren4jConfig.getAliyun().getOss().getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = neilren4jConfig.getAliyun().getAccesskey();
        String accessKeySecret = neilren4jConfig.getAliyun().getSecret();
        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
            ossClient.putObject(neilren4jConfig.getAliyun().getOss().getBucketname(), objectPath, file);
            // 关闭OSSClient。
            ossClient.shutdown();
            //返回文件相对路径
            return objectPath;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
