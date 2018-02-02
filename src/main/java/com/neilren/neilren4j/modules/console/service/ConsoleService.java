package com.neilren.neilren4j.modules.console.service;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.common.security.GoogleAuthenticator;
import com.neilren.neilren4j.common.security.OTP;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class ConsoleService {
    private static Logger log = Logger.getLogger(ConsoleService.class);

    /**
     * 登陆业务
     *
     * @param request
     * @param otp
     * @param gac
     * @param ver
     * @return
     */
    public JsonObject login(HttpServletRequest request, String otp, String gac, String ver) {
        JsonObject jsonObject = new JsonObject();
        HttpSession session = request.getSession();
        if (Global.getConfig("neilren.debug").equals("true")) {
            session.setAttribute("logined", "true");
            jsonObject.setState(200);
            jsonObject.setMessage("登陆成功！");
            return jsonObject;
        }
        //验证验证码
        ver = ver.toLowerCase();
        if (!ver.equals(session.getAttribute("randomString").toString().toLowerCase())) {
            jsonObject.setState(403);
            jsonObject.setMessage("验证码错误！");
            return jsonObject;
        }
        if (gac != "") {
            //验证gac
            if (GoogleAuthenticator.authcode(gac, Global.getConfig("neilren.authcode"))) {
                session.setAttribute("logined", "true");
                jsonObject.setState(200);
                jsonObject.setMessage("登陆成功！");
                return jsonObject;
            } else {
                jsonObject.setState(403);
                jsonObject.setMessage("GoogleAuthenticator验证未通过");
                return jsonObject;
            }
        } else if (otp != "") {
            //验证otp
            OTP Otp = new OTP();
            try {
                if (Otp.verifyYubicoOTP(otp)) {
                    session.setAttribute("logined", "true");
                    jsonObject.setState(200);
                    jsonObject.setMessage("登陆成功！");
                    return jsonObject;
                } else {
                    jsonObject.setState(403);
                    jsonObject.setMessage("OTP验证未通过");
                    return jsonObject;
                }
            } catch (Exception e) {
                jsonObject.setState(500);
                jsonObject.setMessage("发生内部错误：" + e.getMessage());
                log.error(e.getMessage());
                e.printStackTrace();
                return jsonObject;
            }
        } else {
            jsonObject.setState(403);
            jsonObject.setMessage("GAC和OTP必填一项！");
            return jsonObject;
        }
    }
}
