package com.neilren.neilren4j.modules.article.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.entity.Tag;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface TagDao extends CrudDao<Tag> {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKeyWithBLOBs(Tag record);

    List<Tag> selectAllTag();

    List<ArticleWithBLOBs> selectByTagLimit(Map<String,Object> stringObjectMap);

    int selectArticleTotal(String tag);
}