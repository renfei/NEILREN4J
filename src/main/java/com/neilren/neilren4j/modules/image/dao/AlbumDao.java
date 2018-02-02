package com.neilren.neilren4j.modules.image.dao;

import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.image.entity.Album;
import com.neilren.neilren4j.modules.image.entity.AlbumWithBLOBs;

import java.util.List;

@MyBatisDao
public interface AlbumDao {
    int deleteByPrimaryKey(Long id);

    int insert(AlbumWithBLOBs record);

    int insertSelective(AlbumWithBLOBs record);

    int selectImageTotal();

    AlbumWithBLOBs selectByPrimaryKey(Long id);

    List<AlbumWithBLOBs> selectByLimit(int start, int end);

    List<AlbumWithBLOBs> selectAll();

    int updateByPrimaryKeySelective(AlbumWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AlbumWithBLOBs record);

    int updateByPrimaryKey(Album record);
}