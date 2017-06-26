package com.neilren.neilren4j.common.service;

import com.neilren.neilren4j.common.utils.AliyunOSSClientUtil;
import org.springframework.stereotype.Service;
import com.aliyun.oss.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by neil on 25/06/2017.
 */
@Service
public class AliyunService extends BaseService {
    AliyunOSSClientUtil ossClientUtil = new AliyunOSSClientUtil();

    public String updateImg(MultipartFile file) throws IOException {
        if (file == null || file.getSize() <= 0) {
            return null;
        }
        String name = ossClientUtil.uploadImg2Oss(file);
        String imgUrl = ossClientUtil.getImgUrl(name);
        imgUrl = imgUrl.replace("http://bj-neilren.oss-cn-beijing.aliyuncs.com/", "");
        imgUrl = imgUrl.substring(0, imgUrl.indexOf('?'));
        return imgUrl;
    }
}
