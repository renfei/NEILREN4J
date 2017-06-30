package com.neilren.neilren4j.modules.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.common.security.OTP;
import com.neilren.neilren4j.common.service.AliyunService;
import com.neilren.neilren4j.modules.article.entity.ArticleCategory;
import com.neilren.neilren4j.modules.article.entity.ArticleTag;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.CategoryService;
import com.neilren.neilren4j.modules.article.service.TagService;
import com.neilren.neilren4j.modules.system.service.ArticleReleaseService;
import com.yubico.client.v2.exceptions.YubicoValidationFailure;
import com.yubico.client.v2.exceptions.YubicoVerificationException;
import freemarker.ext.beans.HashAdapter;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by neil on 24/06/2017.
 */
@Controller
@RequestMapping(value = "/System")
public class SystemController extends BaseController {
    @Autowired
    private AliyunService aliyunService;
    @Autowired
    private ArticleReleaseService articleReleaseService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService catService;
    private OTP otp = new OTP();

    @RequestMapping(value = "Article/Release", method = RequestMethod.GET)
    public ModelAndView ArticleRelease() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("tagList", tagService.getAllTag());
        mv.addObject("catList", catService.getAllCat());
        mv.setViewName("system/article/release");
        return mv;
    }

    @RequestMapping(value = "Article/Release", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ArticleRelease(@RequestParam String strOtp, @RequestBody String requestBody) throws IOException {
        Map<String, Object> map, retMap;
        retMap = new HashedMap();
        map = JSONObject.toJavaObject(JSONObject.parseObject(requestBody), Map.class);
        //验证OTP合法性
        if (strOtp.length() != 44) {
            retMap.put("state", "fail");
            retMap.put("msg", "OTP一次性密码验证失败");
            return retMap;
        }
        boolean otpVerify;
        try {
            otpVerify = otp.verifyYubicoOTP(strOtp);
        } catch (Exception ex) {
            retMap.put("state", "fail");
            retMap.put("msg", "OTP一次性密码验证失败");
            return retMap;
        }
        if (otpVerify) {
            ArticleWithBLOBs archiveWithBLOBs = articleReleaseService.ArticleRelease(map);
            retMap.put("state", "success");
            retMap.put("msg", archiveWithBLOBs.getId());
        } else {
            retMap.put("state", "fail");
            retMap.put("msg", "OTP一次性密码验证失败");
        }
        return retMap;
    }

    @RequestMapping(value = "Ueditor", method = RequestMethod.GET)
    public void Ueditor(@RequestParam String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("config".equals(action)) {
            OutputStream os = response.getOutputStream();
            IOUtils.copy(SystemController.class.getClassLoader().getResourceAsStream("ueditor-config.json"), os);
        }
    }

    @RequestMapping(value = "Ueditor", method = RequestMethod.POST)
    public void Ueditor(@RequestParam String action, HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "upfile", required = false) MultipartFile upfile) throws IOException {
        if ("uploadimage".equals(action)) {
            if (upfile != null) {
                String ImgUrl = aliyunService.updateImg(upfile);
                Map<String, String> result = Maps.newHashMap();
                String state = "SUCCESS";
                //返回类型
                String rootPath = request.getContextPath();
                result.put("url", ImgUrl);
                result.put("size", String.valueOf(upfile.getSize()));
                result.put("type", ImgUrl.substring(ImgUrl.lastIndexOf(".")));
                result.put("state", state);
                response.getWriter().write(JSONObject.toJSON(result).toString());
            }
        }
    }
}
