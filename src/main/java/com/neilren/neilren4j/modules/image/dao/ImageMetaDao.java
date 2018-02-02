package com.neilren.neilren4j.modules.image.dao;

import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.image.entity.ImageMeta;

@MyBatisDao
public interface ImageMetaDao {
    int deleteByPrimaryKey(Long id);

    int insert(ImageMeta record);

    int insertSelective(ImageMeta record);

    ImageMeta selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImageMeta record);

    int updateByPrimaryKeyWithBLOBs(ImageMeta record);
}