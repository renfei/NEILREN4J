package com.neilren.neilren4j.modules.image.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.neilren.neilren4j.common.service.PagingService;
import com.neilren.neilren4j.common.utils.AliyunOssUtil;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.image.dao.AlbumDao;
import com.neilren.neilren4j.modules.image.dao.ImageDao;
import com.neilren.neilren4j.modules.image.dao.ImageMetaDao;
import com.neilren.neilren4j.modules.image.entity.Album;
import com.neilren.neilren4j.modules.image.entity.AlbumWithBLOBs;
import com.neilren.neilren4j.modules.image.entity.Image;
import com.neilren.neilren4j.modules.image.entity.ImageMeta;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.List;

@Service
public class ImageService {
    private static Logger log = Logger.getLogger(ImageService.class);
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ImageMetaDao imageMetaDao;
    @Autowired
    private PagingService pagingService;

    public List<AlbumWithBLOBs> getAlbums(int page) {
        return getAlbums(page, 24);
    }

    public List<AlbumWithBLOBs> getAlbums(int page, int row) {
        if (page < 1) {
            page = 1;
        }
        int start, end;
        start = (page - 1) * row;
        end = row;
        return albumDao.selectByLimit(start, end);
    }

    /**
     * 获取文章分页的页码
     *
     * @param index
     * @return
     */
    public List<ArticlePaging> getPagingList(int index, int total) {
        if (index <= 0) {
            return null;
        }
        List<ArticlePaging> articlePagingList = null;
        //articlePagingList = (List<ArticlePaging>) memcachedManager.get(memcachedArticlePagingListKey + "_" + index);
        if (articlePagingList == null) {
            articlePagingList = pagingService.getPaging(index, total, 24);
            //memcachedManager.set(memcachedArticlePagingListKey + "_" + index, articlePagingList, Global.MemcachedExpire);
        }
        return articlePagingList;
    }

    public List<ArticlePaging> getAlbumPagingList(int index) {
        return getPagingList(index, getAlbumTotal());
    }

    public List<ArticlePaging> getImagePagingList(Long id, int index) {
        return getPagingList(index, getImageTotal(id));
    }

    public int getImageTotal(Long id) {
        return imageDao.selectImageTotal(id);
    }

    public int getAlbumTotal() {
        return albumDao.selectImageTotal();
    }

    /**
     * 获取所有相册
     *
     * @return
     */
    public List<AlbumWithBLOBs> getAllAlbum() {
        return albumDao.selectAll();
    }

    /**
     * 获取相册下的所以照片
     *
     * @param id
     * @return
     */
    public List<Image> getImages(Long id, int page) {
        int start = (page - 1) * 24;
        return imageDao.getImageAllInfo(id, start, 24);
    }

    /**
     * 上传图片
     *
     * @param upl
     * @return
     */
    public boolean upload(MultipartFile upl, Long albumid) {
        CommonsMultipartFile cf = (CommonsMultipartFile) upl;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        String Url = AliyunOssUtil.upLoad(upl);
        System.out.println(Url);
        Metadata metadata = null;
        try {
            metadata = ImageMetadataReader.readMetadata(file);
        } catch (Exception e) {

        }
        ImageMeta imageMeta = new ImageMeta();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if (tag.getTagName().equals("Image Height")) {
                    imageMeta.setImageheight(tag.getDescription());
                }
                if (tag.getTagName().equals("Image Width")) {
                    imageMeta.setImagewidth(tag.getDescription());
                }
                if (tag.getTagName().equals("Make")) {
                    imageMeta.setMake(tag.getDescription());
                }
                if (tag.getTagName().equals("Model")) {
                    imageMeta.setModel(tag.getDescription());
                }
                if (tag.getTagName().equals("Orientation")) {
                    imageMeta.setOrientation(tag.getDescription());
                }
                if (tag.getTagName().equals("Date/Time")) {
                    imageMeta.setDatetime(tag.getDescription());
                }
                if (tag.getTagName().equals("Exposure Time")) {
                    imageMeta.setExposuretime(tag.getDescription());
                }
                if (tag.getTagName().equals("F-Number")) {
                    imageMeta.setfNumber(tag.getDescription());
                }
                if (tag.getTagName().equals("Exposure Program")) {
                    imageMeta.setExposureprogram(tag.getDescription());
                }
                if (tag.getTagName().equals("ISO Speed Ratings")) {
                    imageMeta.setIsospeedratings(tag.getDescription());
                }
                if (tag.getTagName().equals("Sensitivity Type")) {
                    imageMeta.setSensitivitytype(tag.getDescription());
                }
                if (tag.getTagName().equals("Recommended Exposure Index")) {
                    imageMeta.setRecommendedexposureindex(tag.getDescription());
                }
                if (tag.getTagName().equals("Date/Time Original")) {
                    imageMeta.setDateoriginal(tag.getDescription());
                }
                if (tag.getTagName().equals("Date/Time Digitized")) {
                    imageMeta.setDatedigitized(tag.getDescription());
                }
                if (tag.getTagName().equals("Shutter Speed Value")) {
                    imageMeta.setShutterspeedvalue(tag.getDescription());
                }
                if (tag.getTagName().equals("Aperture Value")) {
                    imageMeta.setAperturevalue(tag.getDescription());
                }
                if (tag.getTagName().equals("Exposure Bias Value")) {
                    imageMeta.setExposurebiasvalue(tag.getDescription());
                }
                if (tag.getTagName().equals("Metering Mode")) {
                    imageMeta.setMeteringmode(tag.getDescription());
                }
                if (tag.getTagName().equals("Flash")) {
                    imageMeta.setFlash(tag.getDescription());
                }
                if (tag.getTagName().equals("Focal Length")) {
                    imageMeta.setFocallength(tag.getDescription());
                }
                if (tag.getTagName().equals("Custom Rendered")) {
                    imageMeta.setCustomrendered(tag.getDescription());
                }
                if (tag.getTagName().equals("Exposure Mode")) {
                    imageMeta.setExposuremode(tag.getDescription());
                }
                if (tag.getTagName().equals("White Balance Mode")) {
                    imageMeta.setWhitebalancemode(tag.getDescription());
                }
                if (tag.getTagName().equals("Scene Capture Type")) {
                    imageMeta.setScenecapturetype(tag.getDescription());
                }
                if (tag.getTagName().equals("Lens Specification")) {
                    imageMeta.setLensspecification(tag.getDescription());
                }
                if (tag.getTagName().equals("Lens Model")) {
                    imageMeta.setLensmodel(tag.getDescription());
                }
            }
        }
        try {
            imageMetaDao.insert(imageMeta);
            Image image = new Image();
            image.setMeteid(imageMeta.getId());
            image.setAlbumid(albumid);
            image.setUrl(Url);
            imageDao.insert(image);
            Album album = albumDao.selectByPrimaryKey(albumid);
            album.setCount(imageDao.selectImageTotal(albumid));
            albumDao.updateByPrimaryKey(album);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
