package com.neilren.neilren4j.modules.api.controller;

import com.neilren.neilren4j.modules.api.entity.Article;
import com.neilren.neilren4j.modules.api.entity.WeChatMessage;
import com.neilren.neilren4j.modules.api.entity.WeChatReply;
import com.neilren.neilren4j.modules.api.service.WeChatService;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by neil on 01/07/2017.
 */
@Controller
@RequestMapping(value = "/API/WeChat")
public class WeChatController {
    @Autowired
    private WeChatService weChatService;

    /**
     * 处理消息
     */
    @RequestMapping(value = "")
    @ResponseBody
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        //if (weChatService.checkSignature(request)) {//仅处理微信服务端发的请求
            WeChatReply reply = new WeChatReply();
            try {
                reply = weChatService.responseMessage(request);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //将回复消息序列化为xml形式
            String back = weChatService.replyToXml(reply);
            response.getWriter().write(back);
        //}
    }
}
