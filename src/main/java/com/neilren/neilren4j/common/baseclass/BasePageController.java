package com.neilren.neilren4j.common.baseclass;

import com.neilren.neilren4j.common.entity.PageHead;
import com.neilren.neilren4j.entity.Footer;
import com.neilren.neilren4j.service.ArticleService;
import com.neilren.neilren4j.service.HolidayService;
import com.neilren.neilren4j.service.SiteMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BasePageController
 * @Description TODO
 * @Date 2018/7/16 17:02
 */
public class BasePageController extends BaseController {
    @Autowired
    protected SiteMenuService siteMenuService;
    @Autowired
    protected HolidayService holidayService;
    @Autowired
    protected ArticleService articleService;
    /**
     * 线程绑定Request对象
     */
    protected ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<>();

    /**
     * 线程绑定Response对象
     */
    protected ThreadLocal<HttpServletResponse> localResponse = new ThreadLocal<>();

    @Autowired
    protected PageHead pageHead;

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param request
     * @param response
     * @param model
     */
    @ModelAttribute
    public void mdeolAttribute(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        localRequest.set(request);
        localResponse.set(response);
        pageHead.setCss(neilren4jConfig.getPagecss());
        pageHead.setJss(neilren4jConfig.getPagejss());
        pageHead.setScript(systemService.gerHeadScript());
        siteName = systemService.getSiteName();
        model.addAttribute("sitename", siteName);
        model.addAttribute("head", pageHead);
        model.addAttribute("holiday", holidayService.getHoliday());
        model.addAttribute("menu", siteMenuService.getAllMenu());
        Footer footer = new Footer();
        model.addAttribute("footer", footer);
    }
}
