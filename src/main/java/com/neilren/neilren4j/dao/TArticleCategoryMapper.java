package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TArticleCategory;
import java.util.List;

public interface TArticleCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleCategory record);

    TArticleCategory selectByPrimaryKey(Long id);

    List<TArticleCategory> selectAll();

    int updateByPrimaryKey(TArticleCategory record);
}