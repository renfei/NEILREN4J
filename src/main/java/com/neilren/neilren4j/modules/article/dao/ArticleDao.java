package com.neilren.neilren4j.modules.article.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.article.entity.Article;
import com.neilren.neilren4j.modules.article.entity.ArticleArchives;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ArticleDao extends CrudDao<Article> {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Long id);

    int updateViewsByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

    List<ArticleWithBLOBs> selectByLimit(@Param("index") int index, @Param("size") int size);

    List<ArticleArchives> selectArchives();

    int selectArticleTotal();

    List<ArticleWithBLOBs> selectTop10ByDate();
    List<ArticleWithBLOBs> searchByLike(String wd);
}