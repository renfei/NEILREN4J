package com.neilren.neilren4j.common.security;

import com.neilren.neilren4j.common.utils.CaptchaUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取验证码类
 */
public class Captcha {
    public static void getCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CaptchaUtil.outputCaptcha(request, response);
    }
}
