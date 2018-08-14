package com.neilren.neilren4j;

import com.neilren.neilren4j.common.util.Encrypt;
import com.neilren.neilren4j.common.util.GoogleAuthenticator;
import com.neilren.neilren4j.entity.BaiduXiongZhangJsonLD;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neilren4jApplicationTests {

    @Test
    public void contextLoads() {
        BaiduXiongZhangJsonLD baiduXiongZhangJsonLD = new BaiduXiongZhangJsonLD(
                "http://www.baidu.com/23",
                "标题",
                new ArrayList<>(),
                new Date(),
                "1233456"
        );
        System.out.println(baiduXiongZhangJsonLD.getHtmlCode(baiduXiongZhangJsonLD));
    }

}
