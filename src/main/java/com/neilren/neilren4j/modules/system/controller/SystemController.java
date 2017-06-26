package com.neilren.neilren4j.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.common.service.AliyunService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by neil on 24/06/2017.
 */
@Controller
@RequestMapping(value = "/System")
public class SystemController extends BaseController {
    @Autowired
    private AliyunService aliyunService;

    @RequestMapping(value = "Article/Release")
    public ModelAndView ArticleRelease() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/article/release");
        return mv;
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
            if(upfile!=null) {
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
