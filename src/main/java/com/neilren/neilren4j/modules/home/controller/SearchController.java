package com.neilren.neilren4j.modules.home.controller;

import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.modules.article.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by neil on 14/06/2017.
 */
@Controller
public class SearchController extends BaseController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/Search/")
    public ModelAndView SearchArticle(String wd) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("articleWithBLOBsList", searchService.Search(wd));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        mv.setViewName("search/page");
        return mv;
    }
}
