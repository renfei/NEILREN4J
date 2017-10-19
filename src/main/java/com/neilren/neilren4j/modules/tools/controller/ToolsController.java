package com.neilren.neilren4j.modules.tools.controller;

import com.neilren.neilren4j.common.dao.IPDBDao;
import com.neilren.neilren4j.common.entity.IPDBObject;
import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.common.security.OTP;
import com.neilren.neilren4j.common.security.XssAndSqlHttpServletRequestWrapper;
import com.neilren.neilren4j.common.service.IPDBService;
import com.neilren.neilren4j.common.utils.RequestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by neil on 09/08/2017.
 */
@Controller
@RequestMapping(value = "/tools")
public class ToolsController {
    @Autowired
    private IPDBService ipdbService;
    @Autowired
    private RequestUtils requestUtils;

    @RequestMapping(value = "bjyikatong")
    public ModelAndView BMACCardRecord() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tools/bjyikatong");
        return mv;
    }

    @RequestMapping(value = "ip")
    public ModelAndView IPDataBase(@RequestParam(value = "ip", required = false) String IP, HttpServletRequest request) {
        IPDBObject ipdbObject = null;
        IP = XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(IP);
        if (IP != null && ipdbService.isboolIp(IP)) {
            //查询指定的IP信息
            ipdbObject = ipdbService.queryByIP(IP);
        } else {
            IP = requestUtils.getIpAddr(request);
            //查询来访者的IP信息
            ipdbObject = ipdbService.queryByIP(IP);
        }
        if (ipdbObject == null)
            ipdbObject = new IPDBObject();
        ModelAndView mv = new ModelAndView();
        mv.addObject("ipdbObject", ipdbObject);
        mv.addObject("IP", IP);
        mv.setViewName("tools/ip");
        return mv;
    }
}
