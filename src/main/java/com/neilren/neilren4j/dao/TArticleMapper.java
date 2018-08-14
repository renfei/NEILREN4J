package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TArticle;
import com.neilren.neilren4j.dbentity.TCategory;
import com.neilren.neilren4j.dbentity.TTag;
import com.neilren.neilren4j.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticle record);

    Article selectByPrimaryKey(Long id);

    List<TArticle> selectAll();

    List<Article> selectByTagIdAndArticleId(@Param("tagid") Long tagid, @Param("articleid") Long articleid);

    List<Article> selectAllArticle();

    List<Article> selectArticleListByTag(@Param("tag") TTag tag);

    List<Article> selectArticleListByCat(@Param("cat") TCategory cat);

    List<Article> selectTopViewArticle();

    int updateByPrimaryKey(TArticle record);
}