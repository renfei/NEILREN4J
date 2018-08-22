package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.common.entity.APIResult;
import com.neilren.neilren4j.common.util.DomainUtils;
import com.neilren.neilren4j.dbentity.TFrielink;
import com.neilren.neilren4j.entity.Forms;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.security.XssAndSqlHttpServletRequestWrapper;
import com.neilren.neilren4j.service.FrielinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName LinkController
 * @Description TODO
 * @Date 2018/7/19 22:16
 */
@Controller
@RequestMapping("/link")
public class LinkController extends BasePageController {
    @Autowired
    private FrielinkService frielinkService;
    @Autowired
    private DomainUtils domainUtils;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        HeadTitle headTitle = new HeadTitle("友情链接申请 - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("link/register");
        return mv;
    }

    @RequestMapping(value = "register/save", method = RequestMethod.POST)
    @ResponseBody
    public APIResult saveRegister(@ModelAttribute Forms forms) {
        APIResult apiResult = new APIResult();
        if (forms.getLink().indexOf("http://") == -1 && forms.getLink().indexOf("https://") == -1) {
            apiResult.setSuccess(false);
            apiResult.setMessage("您填写的【链接】没有包含协议头，请添加【http://或者https://】");
            return apiResult;
        }
        if (!domainUtils.domainVerification(forms.getDomain())) {
            apiResult.setSuccess(false);
            apiResult.setMessage("您填写的【链接】似乎有点问题，未能通过验证，请检查一下再次提交");
            return apiResult;
        }
        if (!Pattern.compile("^[a-zA-Z0-9_-|\\\\.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$").matcher(forms.getEmail()).find()) {
            apiResult.setSuccess(false);
            apiResult.setMessage("您填写的【邮箱】似乎有点问题，未能通过验证，请检查一下再次提交");
            return apiResult;
        }
        if (!Pattern.compile("^\\d{5,10}$").matcher(forms.getQq()).find()) {
            apiResult.setSuccess(false);
            apiResult.setMessage("您填写的【QQ】似乎有点问题，未能通过验证，请检查一下再次提交");
            return apiResult;
        }
        try {
            //检测是否重复提交
            if (frielinkService.getFrieLinkByDomain(forms.getDomain()) != null) {
                apiResult.setSuccess(false);
                apiResult.setMessage("您填写的网站已经在我数据库中存储，请勿重复提交！\n如需变更请邮件联系：ren.fei.cn@gmail.com");
                return apiResult;
            }
            //网站名称危险过滤
            forms.setSitename(XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(forms.getSitename()));
            //保存到数据库
            TFrielink frielink = frielinkService.saveFrieLinkByForm(forms);
            //首次审核线程
            frielinkService.firstAudit(frielink);
            apiResult.setSuccess(true);
            apiResult.setMessage("提交成功！\n您的网站名称:" + frielink.getSitename() + "\n您的网站链接：" +
                    frielink.getLink().trim() + "\n您的邮箱地址：" + frielink.getEmail() + "\n已经进入自动审核流程，审核结果将发送到您的邮箱");
            return apiResult;
        } catch (Exception e) {
            apiResult.setSuccess(false);
            apiResult.setMessage("服务器发生内部错误！服务暂时不可用");
            return apiResult;
        }
    }
}
