package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TCategory record);

    TCategory selectByPrimaryKey(Long id);

    TCategory selectByCatEnName(@Param("ename") String enName);

    List<TCategory> selectAll();

    int updateByPrimaryKey(TCategory record);
}