package com.neilren.neilren4j.controller.account;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.common.entity.APIResult;
import com.neilren.neilren4j.common.util.GoogleAuthenticator;
import com.neilren.neilren4j.common.util.IpUtil;
import com.neilren.neilren4j.dbentity.TIp;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.LogAccess;
import com.neilren.neilren4j.entity.User;
import com.neilren.neilren4j.service.IPService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName AccountController
 * @Description TODO
 * @Date 2018/7/28 14:28
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BasePageController {
    @Autowired
    private GoogleAuthenticator googleAuthenticator;
    @Autowired
    private IPService ipService;

    @RequestMapping("signin")
    public ModelAndView signinPage() {
        ModelAndView mv = new ModelAndView();
        HeadTitle headTitle = new HeadTitle("Sign in - NeilRen Accounts - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("account/signin");
        return mv;
    }

    @RequestMapping(value = "doSignin", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<String> doSignin(String account, String passwd, String vcode,
                                      @RequestParam(value = "totp", required = false) String totp) {
        APIResult<String> result = new APIResult<String>();
        if (localRequest.get().getSession().getAttribute("vrifyCode") == null) {
            result.setSuccess(false);
            result.setMessage("警告：非法请求！请重试");
            LogAccess logAccess = new LogAccess(localRequest.get());
            StringBuffer sb = new StringBuffer();
            sb.append("警告：发现疑似未授权非法使用登陆接口，请勿尝试使用接口撞库！您的信息已经被记录：");
            sb.append("IP:" + logAccess.getIp() + ";");
            sb.append("Browser:" + logAccess.getBrowserName() + ";");
            sb.append("Version:" + logAccess.getBrowserVersion() + ";");
            sb.append("OS:" + logAccess.getOsName() + ";");
            IpUtil ipUtil = new IpUtil();
            TIp ip = ipService.selectByIP(ipUtil.getIpAddr(localRequest.get()));
            sb.append("IP:" + ipUtil.getIpAddr(localRequest.get()) + ";");
            if (ip != null)
                sb.append("address:" + ip.getCountry() + " " + ip.getLocal() + ";");
            result.setData(sb.toString());
            return result;
        }
        if (account == null || passwd == null || vcode == null) {
            result.setSuccess(false);
            result.setMessage("请求参数缺失，请求未被执行");
            return result;
        }
        //验证验证码是否正确
        String vrifyCode = localRequest.get().getSession().getAttribute("vrifyCode").toString();
        if (vcode.equals(vrifyCode)) {
            //验证账户密码是否正确
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account, passwd);
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(usernamePasswordToken);   //完成登录
                User user = (User) subject.getPrincipal();
                if (user.getSecret() != null && !"".equals(user.getSecret())) {
                    if (totp == null || "".equals(totp)) {
                        //需要TOTP
                        result.setSuccess(true);
                        result.setMessage("totp");
                        return result;
                    }
                    //需要验证 otp 是否正确
                    if (googleAuthenticator.authcode(totp, user.getSecret())) {
                        localRequest.get().getSession().setAttribute("user", user);
                        localRequest.get().getSession().removeAttribute("vrifyCode");
                        //登陆成功
                        result.setSuccess(true);
                        result.setMessage("Success!");
                    } else {
                        result.setSuccess(false);
                        result.setMessage("TOTP验证失败");
                    }
                } else {
                    localRequest.get().getSession().setAttribute("user", user);
                    localRequest.get().getSession().removeAttribute("vrifyCode");
                    //登陆成功
                    result.setSuccess(true);
                    result.setMessage("Success!");
                }
            } catch (IncorrectCredentialsException ice) {
                localRequest.get().getSession().removeAttribute("vrifyCode");
                // 捕获密码错误异常
                result.setSuccess(false);
                result.setMessage("密码错误");
            } catch (UnknownAccountException uae) {
                // 捕获未知用户名异常
                result.setSuccess(false);
                result.setMessage("用户名不存在");
                localRequest.get().getSession().removeAttribute("vrifyCode");
            } catch (ExcessiveAttemptsException eae) {
                // 捕获错误登录过多的异常
                localRequest.get().getSession().removeAttribute("vrifyCode");
                result.setSuccess(false);
                result.setMessage("登录失败多次，请稍后再试");
            } catch (AuthenticationException ae) {
                //其他错误
                localRequest.get().getSession().removeAttribute("vrifyCode");
                result.setSuccess(false);
                result.setMessage("登录失败");
            } catch (Exception e) {
                localRequest.get().getSession().removeAttribute("vrifyCode");
                result.setSuccess(false);
                result.setMessage(e.getMessage());
            }
        } else {
            localRequest.get().getSession().removeAttribute("vrifyCode");
            result.setSuccess(false);
            result.setMessage("验证码错误");
        }

        return result;
    }
}
