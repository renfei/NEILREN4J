package com.neilren.neilren4j.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.neilren.neilren4j.common.baseclass.BasePageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName VerifyImage
 * @Description TODO
 * @Date 2018/8/1 16:55
 */
@Controller
@RequestMapping("VerifyImage")
public class VerifyImage extends BasePageController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("get")
    public void getVerifyImage() throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            localRequest.get().getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            localResponse.get().sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        localResponse.get().setHeader("Cache-Control", "no-store");
        localResponse.get().setHeader("Pragma", "no-cache");
        localResponse.get().setDateHeader("Expires", 0);
        localResponse.get().setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                localResponse.get().getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

    }
}
