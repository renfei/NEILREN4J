package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.common.entity.APIResult;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.Search;
import com.neilren.neilren4j.service.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SearchController
 * @Description TODO
 * @Date 2018/7/19 22:14
 */
@Controller
@RequestMapping({"/Search", "/Search/"})
public class SearchController extends BasePageController {
    @Autowired
    private SearchService openSearchService;

    @RequestMapping("")
    public ModelAndView Search(String wd) {
        List<Search> searchList = openSearchService.search(wd, 1, 10);
        ModelAndView mv = new ModelAndView();
        int total = 0;
        if (searchList != null && searchList.size() > 0) {
            total = searchList.get(0).getTotal();
            mv.addObject("searchList", searchList);
            mv.addObject("searchtime", searchList.get(0).getSearchtime());
        }
        mv.addObject("wd", wd);
        mv.addObject("total", total);
        HeadTitle headTitle = new HeadTitle("Search:" + wd + " - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping("paging")
    @ResponseBody
    public APIResult Search(String wd, int page) {
        APIResult apiResult = new APIResult();
        List<Search> searchList = openSearchService.search(wd, page, 10);
        apiResult.setSuccess(true);
        apiResult.setData(searchList);
        return apiResult;
    }
}
