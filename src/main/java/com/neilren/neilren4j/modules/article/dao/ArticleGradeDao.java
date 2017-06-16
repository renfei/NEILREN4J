package com.neilren.neilren4j.modules.article.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.article.entity.ArticleGrade;

@MyBatisDao
public interface ArticleGradeDao extends CrudDao<ArticleGrade> {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleGrade record);

    int insertSelective(ArticleGrade record);

    ArticleGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleGrade record);

    int updateByPrimaryKey(ArticleGrade record);
}