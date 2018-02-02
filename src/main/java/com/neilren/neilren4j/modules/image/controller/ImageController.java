package com.neilren.neilren4j.modules.image.controller;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.modules.image.entity.AlbumWithBLOBs;
import com.neilren.neilren4j.modules.image.entity.Image;
import com.neilren.neilren4j.modules.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 影像Controller
 */
@Controller
@RequestMapping("/Image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * 相册列表
     *
     * @param page
     * @return
     */
    @RequestMapping("")
    public ModelAndView getImges(@RequestParam(value = "page", required = false) String page) {
        ModelAndView mv = new ModelAndView();
        int Page = 1;
        if (page != null && page != "") {
            try {
                Page = Integer.parseInt(page);
            } catch (Exception e) {
                return new ModelAndView("redirect:/Image");
            }
        }
        mv.addObject("Index", Page);
        mv.addObject("Albums", imageService.getAlbums(Page));
        mv.addObject("articlePagingList", imageService.getAlbumPagingList(Page));
        mv.setViewName("image/index");
        return mv;
    }

    /**
     * 相册详情
     *
     * @param id
     * @param page
     * @return
     */
    @RequestMapping("{id}")
    public ModelAndView getImges(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) String page) {
        ModelAndView mv = new ModelAndView();
        int Page = 1;
        if (page != null && page != "") {
            try {
                Page = Integer.parseInt(page);
            } catch (Exception e) {
                return new ModelAndView("redirect:/Image");
            }
        }
        List<Image> images = imageService.getImages(id, Page);
        mv.addObject("Index", Page);
        mv.addObject("articlePagingList", imageService.getImagePagingList(id, Page));
        mv.addObject("images", images);
        mv.addObject("jsons", JSON.toJSON(images).toString());
        mv.setViewName("image/album");
        return mv;
    }

    /**
     * 获取所有相册
     *
     * @return
     */
    @RequestMapping("getAllAlbum")
    @ResponseBody
    public List<AlbumWithBLOBs> getAllAlbum() {
        return imageService.getAllAlbum();
    }
}
