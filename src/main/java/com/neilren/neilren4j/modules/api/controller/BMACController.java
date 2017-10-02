package com.neilren.neilren4j.modules.api.controller;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.modules.api.entity.BMACCardRecord;
import com.neilren.neilren4j.modules.api.service.BMACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by neil on 09/08/2017.
 */
@Controller
@ResponseBody
@RequestMapping(value = "/API/BMAC", method = RequestMethod.POST)
public class BMACController {
    @Autowired
    private BMACService bmacService;

    @RequestMapping(value = "inquiryCardRecord")
    public void inquiryCardRecord(
            @RequestParam("cardID") String ID,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            List<BMACCardRecord> bmacCardRecords = bmacService.inquiryCardRecord(ID, pageNo);
            response.getWriter().write(JSON.toJSON(bmacCardRecords).toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
