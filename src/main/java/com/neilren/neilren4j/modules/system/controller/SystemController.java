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
import com.neilren.neilren4j.modules.article.service.TagService;
import com.yubico.client.v2.exceptions.YubicoValidationFailure;
import com.yubico.client.v2.exceptions.YubicoVerificationException;
import freemarker.ext.beans.HashAdapter;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
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
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    private OTP otp = new OTP();

    @RequestMapping(value = "Article/Release", method = RequestMethod.GET)
    public ModelAndView ArticleRelease() {
        ModelAndView mv = new ModelAndView();
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
        boolean otpVerify;
        try {
            otpVerify = otp.verifyYubicoOTP(strOtp);
        } catch (Exception ex) {
            retMap.put("state", "fail");
            retMap.put("msg", "OTP一次性密码验证失败");
            return retMap;
        }
        if (otpVerify) {
            //插入文章
            ArticleWithBLOBs archiveWithBLOBs = new ArticleWithBLOBs();
            archiveWithBLOBs.setArticleDat(new Date());
            archiveWithBLOBs.setViews(0L);
            archiveWithBLOBs.setArticleStatus(0);
            archiveWithBLOBs.setArticleType(Integer.parseInt(map.get("type").toString()));
            archiveWithBLOBs.setTitle(map.get("title").toString());
            archiveWithBLOBs.setAuthor(map.get("author").toString());
            archiveWithBLOBs.setAuthorUrl(map.get("author_link").toString());
            archiveWithBLOBs.setSourceUrl(map.get("source").toString());
            archiveWithBLOBs.setKeyword(map.get("keyword").toString());
            archiveWithBLOBs.setDescribes(map.get("describes").toString());
            archiveWithBLOBs.setContent(map.get("content").toString());
            Long id = articleService.insterArticle(archiveWithBLOBs);
            //插入标签关系
            JSONArray jsonTagArray = (JSONArray) map.get("tag");
            for (int i = 0; i < jsonTagArray.size(); i++) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(archiveWithBLOBs.getId());
                articleTag.setTagId(Long.parseLong(jsonTagArray.getString(i)));
                tagService.insterArticleTag(articleTag);
            }
            //插入分类关系
            JSONArray jsonCatArray = (JSONArray) map.get("cat");
            for (int i = 0; i < jsonCatArray.size(); i++) {
                ArticleCategory articleCategory = new ArticleCategory();
                articleCategory.setArticleId(archiveWithBLOBs.getId());
                articleCategory.setCategoryId(Long.parseLong(jsonCatArray.getString(i)));
                articleService.insterArticleCat(articleCategory);
            }
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
