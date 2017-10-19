package com.neilren.neilren4j.modules.api.controller;

import com.neilren.neilren4j.common.entity.IPDBObject;
import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.common.service.IPDBService;
import com.neilren.neilren4j.modules.api.entity.IPDBJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/API/IPDB", method = RequestMethod.POST)
public class IPDBController {
    @Autowired
    private IPDBService ipdbService;

    @ResponseBody
    @RequestMapping("query")
    public JsonObject query(@RequestParam(value = "ip", required = false) String IP) {
        JsonObject jsonObject = new JsonObject();
        if (IP == null) {
            jsonObject.setState(500);
            jsonObject.setMessage("IP是必要的参数，请检查您的请求");
            return jsonObject;
        }
        if (ipdbService.isboolIp(IP)) {
            IPDBObject ipdbObject = null;
            try {
                ipdbObject = ipdbService.queryByIP(IP);
            } catch (Exception ex) {
                jsonObject.setState(500);
                jsonObject.setMessage("IP查询时发生内部服务器异常");
                return jsonObject;
            }
            if (ipdbObject == null) {
                jsonObject.setState(500);
                jsonObject.setMessage("未查询到该IP地址的信息");
                return jsonObject;
            }
            IPDBJsonObject ipdbJsonObject = new IPDBJsonObject();
            ipdbJsonObject.setIP(IP);
            ipdbJsonObject.setCountry(ipdbObject.getCountry());
            ipdbJsonObject.setLocal(ipdbObject.getLocal());
            ipdbJsonObject.setVersion(ipdbObject.getVersion());
            jsonObject.setState(200);
            jsonObject.setMessage("Success");
            jsonObject.setObject(ipdbJsonObject);
            return jsonObject;
        } else {
            jsonObject.setState(500);
            jsonObject.setMessage("IP格式不正确，请检查");
        }
        return jsonObject;
    }
}
