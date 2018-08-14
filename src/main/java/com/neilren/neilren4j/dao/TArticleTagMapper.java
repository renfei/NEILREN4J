package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TArticleTag;
import java.util.List;

public interface TArticleTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleTag record);

    TArticleTag selectByPrimaryKey(Long id);

    List<TArticleTag> selectAll();

    int updateByPrimaryKey(TArticleTag record);
}