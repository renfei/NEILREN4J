package com.neilren.neilren4j.modules.article.dao;

import com.neilren.neilren4j.common.persistence.CrudDao;
import com.neilren.neilren4j.common.persistence.annotation.MyBatisDao;
import com.neilren.neilren4j.modules.article.entity.Comment;
import com.neilren.neilren4j.modules.article.entity.Comments;

import java.util.List;

@MyBatisDao
public interface CommentsDao extends CrudDao<Comments> {
    int insert(Comments comments);

    List<Comments> selectByArticleID(Long ArticleID);

    List<Comments> selectAllByArticleID(Long ArticleID);

    int update(Comments comments);
}
