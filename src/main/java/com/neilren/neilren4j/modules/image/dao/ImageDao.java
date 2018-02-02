package com.neilren.neilren4j.modules.image.dao;

import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.image.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ImageDao {
    int deleteByPrimaryKey(Long id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Long id);

    List<Image> selectByAlbumId(@Param("id") Long id, @Param("start") int start, @Param("rows") int rows);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKeyWithBLOBs(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> getImageAllInfo(@Param("id") Long id, @Param("start") int start, @Param("rows") int rows);

    int selectImageTotal(@Param("id") Long id);
}