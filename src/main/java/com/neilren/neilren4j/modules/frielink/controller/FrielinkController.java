package com.neilren.neilren4j.modules.frielink.controller;

import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.common.security.XssAndSqlHttpServletRequestWrapper;
import com.neilren.neilren4j.common.utils.DomainUtils;
import com.neilren.neilren4j.modules.frielink.entity.Forms;
import com.neilren.neilren4j.modules.frielink.entity.Frielink;
import com.neilren.neilren4j.modules.frielink.service.FrielinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;

/**
 * 友情链接 Controller
 */
@Controller
@RequestMapping("/link")
public class FrielinkController extends BaseController {
    @Autowired
    private FrielinkService frielinkService;
    @Autowired
    private DomainUtils domainUtils;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("link/register");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "register/save", method = RequestMethod.POST)
    public JsonObject saveRegister(@ModelAttribute Forms forms) {
        JsonObject jsonObject = new JsonObject();
        if (forms.getLink().indexOf("http://") == -1 && forms.getLink().indexOf("https://") == -1) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【链接】没有包含协议头，请添加【http://或者https://】");
            return jsonObject;
        }
        if (!domainUtils.domainVerification(forms.getDomain())) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【链接】似乎有点问题，未能通过验证，请检查一下再次提交");
            return jsonObject;
        }
        if (!Pattern.compile("^[a-zA-Z0-9_-|\\\\.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$").matcher(forms.getEmail()).find()) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【邮箱】似乎有点问题，未能通过验证，请检查一下再次提交");
            return jsonObject;
        }
        if (!Pattern.compile("^\\d{5,10}$").matcher(forms.getQq()).find()) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【QQ】似乎有点问题，未能通过验证，请检查一下再次提交");
            return jsonObject;
        }
        try {
            //检测是否重复提交
            if (frielinkService.getFrieLinkByDomain(forms.getDomain()) != null) {
                jsonObject.setState(500);
                jsonObject.setMessage("您填写的网站已经在我数据库中存储，请勿重复提交！\n如需变更请邮件联系：mail@neilren.com");
                return jsonObject;
            }
            //网站名称危险过滤
            forms.setSitename(XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(forms.getSitename()));
            //保存到数据库
            Frielink frielink = frielinkService.saveFrieLinkByForm(forms);
            //首次审核线程
            frielinkService.firstAudit(frielink);
            jsonObject.setState(200);
            jsonObject.setMessage("提交成功！\n您的网站名称:" + frielink.getSitename() + "\n您的网站链接：" +
                    frielink.getLink().trim() + "\n您的邮箱地址：" + frielink.getEmail() + "\n已经进入自动审核流程，审核结果将发送到您的邮箱");
            return jsonObject;
        } catch (Exception e) {
            jsonObject.setState(500);
            jsonObject.setMessage("服务器发生内部错误！服务暂时不可用");
            return jsonObject;
        }
    }
}
