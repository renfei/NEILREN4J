package com.neilren.neilren4j.modules.article.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 文章评论实体类
 */
public class Comment extends Comments {
    private Long id;
    private Long article_id;
    private Long parent_id;
    private String author_name;
    private String author_email;
    private String author_url;
    private String author_IP;
    private Date comment_date;
    private String comment_content;
    private int approved;
    private String agent;
    private List<Comment> replyCustomer;
    private String reply_name;
    private String reply_url;

    public String getReply_name() {
        return reply_name;
    }

    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }

    public String getReply_url() {
        return reply_url;
    }

    public void setReply_url(String reply_url) {
        this.reply_url = reply_url;
    }

    public void convertFromComments(Comments comments) {
        this.id = comments.getId();
        this.article_id = comments.getArticle_id();
        this.parent_id = comments.getParent_id();
        this.author_name = comments.getAuthor_name();
        this.author_email = comments.getAuthor_email();
        this.author_url = comments.getAuthor_url();
        this.author_IP = comments.getAuthor_IP();
        this.comment_date = comments.getComment_date();
        this.comment_content = comments.getComment_content();
        this.approved = comments.getApproved();
        this.agent = comments.getAgent();
    }

    public List<Comment> getReplyCustomer() {
        return replyCustomer;
    }

    public void setReplyCustomer(List<Comment> replyCustomer) {
        this.replyCustomer = replyCustomer;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getArticle_id() {
        return article_id;
    }

    @Override
    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    @Override
    public Long getParent_id() {
        return parent_id;
    }

    @Override
    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String getAuthor_name() {
        return author_name;
    }

    @Override
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    @Override
    public String getAuthor_email() {
        return author_email;
    }

    @Override
    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    @Override
    public String getAuthor_url() {
        return author_url;
    }

    @Override
    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    @Override
    public String getAuthor_IP() {
        return author_IP;
    }

    @Override
    public void setAuthor_IP(String author_IP) {
        this.author_IP = author_IP;
    }

    @Override
    public Date getComment_date() {
        return comment_date;
    }

    @Override
    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    @Override
    public String getComment_content() {
        return comment_content;
    }

    @Override
    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    @Override
    public int getApproved() {
        return approved;
    }

    @Override
    public void setApproved(int approved) {
        this.approved = approved;
    }

    @Override
    public String getAgent() {
        return agent;
    }

    @Override
    public void setAgent(String agent) {
        this.agent = agent;
    }
}
