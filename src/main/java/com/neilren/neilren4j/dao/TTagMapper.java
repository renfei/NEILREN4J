package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTag record);

    TTag selectByPrimaryKey(Long id);

    TTag selectByArticleId(Long id);

    TTag selectByTagEnName(@Param("enName") String enName);

    List<TTag> selectAll();

    int updateByPrimaryKey(TTag record);
}