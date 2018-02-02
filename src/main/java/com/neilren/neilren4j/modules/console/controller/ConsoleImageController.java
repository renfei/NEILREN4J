package com.neilren.neilren4j.modules.console.controller;

import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.modules.image.dao.AlbumDao;
import com.neilren.neilren4j.modules.image.dao.ImageDao;
import com.neilren.neilren4j.modules.image.entity.AlbumWithBLOBs;
import com.neilren.neilren4j.modules.image.entity.Image;
import com.neilren.neilren4j.modules.image.service.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/console/image")
public class ConsoleImageController {
    private static Logger log = Logger.getLogger(ConsoleImageController.class);

    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ImageService imageService;

    @RequestMapping("album")
    public ModelAndView manageAlbum() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("console/image/album");
        return mv;
    }

    @RequestMapping("image")
    public ModelAndView manageImage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("console/image/image");
        return mv;
    }

    @RequestMapping("getimages")
    @ResponseBody
    public List<Image> getImages(Long id, int page, int rows) {
        int strat = (page - 1) * rows;
        return imageDao.selectByAlbumId(id, strat, rows);
    }

    @RequestMapping("uploadimage")
    @ResponseBody
    public JsonObject uploadImage(MultipartFile upl, Long albumid) {
        JsonObject jsonObject = new JsonObject();
        if (imageService.upload(upl, albumid)) {
            jsonObject.setState(200);
        } else {
            jsonObject.setState(500);
        }
        return jsonObject;
    }

    @RequestMapping(value = "getalbumbyid", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject getAlbumByID(Long id) {
        JsonObject jsonObject = new JsonObject();
        AlbumWithBLOBs albumWithBLOBs = albumDao.selectByPrimaryKey(id);
        if (albumWithBLOBs != null) {
            jsonObject.setState(200);
            jsonObject.setObject(albumWithBLOBs);
        } else {
            jsonObject.setState(404);
            jsonObject.setMessage("未查询到该相册");
        }
        return jsonObject;
    }
}
