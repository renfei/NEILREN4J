package com.neilren.neilren4j.modules.article.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.article.entity.Frielink;
import com.neilren.neilren4j.modules.article.entity.FrielinkWithBLOBs;

import java.util.List;

@MyBatisDao
public interface FrielinkDao extends CrudDao<Frielink> {
    int deleteByPrimaryKey(Long id);

    int insert(FrielinkWithBLOBs record);

    int insertSelective(FrielinkWithBLOBs record);

    FrielinkWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FrielinkWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FrielinkWithBLOBs record);

    int updateByPrimaryKey(Frielink record);

    List<FrielinkWithBLOBs> selectForAll();
}