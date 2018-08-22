package com.neilren.neilren4j.controller.api.open;

import com.neilren.neilren4j.common.entity.APIResult;
import com.neilren.neilren4j.controller.api.APIController;
import com.neilren.neilren4j.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName OpenAPIController
 * @Description TODO
 * @Date 2018/8/21 22:03
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/open", method = RequestMethod.POST)
public class OpenAPIController extends APIController {
    @Autowired
    private SecurityService securityService;

    @RequestMapping("checkWoestPassword")
    public APIResult checkWoestPassword(String pwd) {
        APIResult apiResult = new APIResult();
        try {
            apiResult.setData(securityService.checkWoestPassword(pwd));
            apiResult.setSuccess(true);
            apiResult.setMessage("Success!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            apiResult.setSuccess(false);
            apiResult.setMessage("服务器内部发生异常！");
        }
        apiResult.setDatetime(new Date(System.currentTimeMillis()));
        return apiResult;
    }
}
