package com.neilren.neilren4j.modules.article.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.article.entity.ArticleTag;

@MyBatisDao
public interface ArticleTagDao extends CrudDao<ArticleTag> {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleTag record);

    int insertSelective(ArticleTag record);

    ArticleTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleTag record);

    int updateByPrimaryKey(ArticleTag record);
}