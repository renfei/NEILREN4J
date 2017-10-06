package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.entity.AliyunGreenWeb;
import com.neilren.neilren4j.common.entity.EmailObject;
import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.common.security.XssAndSqlHttpServletRequestWrapper;
import com.neilren.neilren4j.common.utils.AliyunGreenWebUtil;
import com.neilren.neilren4j.common.utils.EmailUtils;
import com.neilren.neilren4j.modules.article.dao.CommentsDao;
import com.neilren.neilren4j.modules.article.entity.Comment;
import com.neilren.neilren4j.modules.article.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CommentService {

    private static String memcachedArticleCommentKey = "ArticleComment_";

    @Resource
    private CommentsDao commentsDao;
    @Resource
    private EmailUtils emailUtils;
    @Autowired
    private MemcachedManager memcachedManager;

    public JsonObject insterComment(Comment comment) {
        JsonObject jsonObject = new JsonObject();
        if (comment == null) {
            jsonObject.setState(500);
            jsonObject.setMessage("评论对象传入为空！");
            return jsonObject;
        }
        if (comment.getAuthor_name().length() > 100) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【称呼】过长，内容最长100字，请检查一下再次提交");
            return jsonObject;
        }
        if (comment.getAuthor_url().length() > 200) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【链接】过长，内容最长200字，请检查一下再次提交");
            return jsonObject;
        }
        if (comment.getComment_content().length() > 4000) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【内容】过长，内容最长4000字，请检查一下再次提交");
            return jsonObject;
        }
        //检查安全性
        comment.setAuthor_email(XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(comment.getAuthor_email()));
        comment.setAuthor_name(XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(comment.getAuthor_name()));
        comment.setComment_content(XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(comment.getComment_content()));
        comment.setAuthor_url(XssAndSqlHttpServletRequestWrapper.stripXSSAndSql(comment.getAuthor_url()));
        //业务逻辑
        comment.setComment_date(new Date());
        comment.setApproved(0);
        if (!comment.getAuthor_url().equals("null")) {
            if (comment.getAuthor_url().indexOf("http://") == -1 && comment.getAuthor_url().indexOf("https://") == -1) {
                jsonObject.setState(500);
                jsonObject.setMessage("您填写的【链接】没有包含协议头，请添加【http://或者https://】，请检查一下再次提交");
                return jsonObject;
            }
        } else {
            comment.setAuthor_url(null);
        }
        if (!Pattern.compile("^[a-zA-Z0-9_-|\\\\.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$").matcher(comment.getAuthor_email()).find()) {
            jsonObject.setState(500);
            jsonObject.setMessage("您填写的【邮箱】似乎有点问题，未能通过验证，请检查一下再次提交");
            return jsonObject;
        }
        int i = commentsDao.insert(comment);
        if (i == 1) {
            jsonObject.setState(200);
            jsonObject.setMessage("评论成功！本站使用缓存机制，请等待缓存更新");
            FirstCommentAuditThread firstCommentAuditThread = new FirstCommentAuditThread(comment);
            firstCommentAuditThread.start();
            return jsonObject;
        } else {
            jsonObject.setState(500);
            jsonObject.setMessage("内部服务器错误！");
            return jsonObject;
        }
    }

    public List<Comment> getCommentByArticleID(String articleID) {
        List<Comment> commentList = (List<Comment>) memcachedManager.get(memcachedArticleCommentKey + articleID);
        if (commentList != null) {
            return commentList;
        } else {
            Long lArticleID = 0L;
            try {
                lArticleID = Long.parseLong(articleID);
            } catch (Exception e) {
                return null;
            }
            commentList = setCommentsToComment(commentsDao.selectByArticleID(lArticleID));
            memcachedManager.set(memcachedArticleCommentKey + articleID, commentList, Global.MemcachedExpire);
            return commentList;
        }
    }

    /**
     * 评论列表，数据库实体对象转换成业务使用的评论列表对象
     *
     * @param allCommentsList 数据库返回的评论列表对象
     * @return 业务中使用的评论列表对象
     */
    private List<Comment> setCommentsToComment(List<Comments> allCommentsList) {
        List<Comments> findRootComments = findRootComments(allCommentsList);
        List<Comment> commentList2 = new ArrayList<Comment>();
        //遍历根评论
        for (Comments comments : findRootComments) {
            Comment comment = new Comment();
            comment.convertFromComments(comments);
            List<Comment> replys = new ArrayList<Comment>(); // 实例化回复的集合
            comment.setReplyCustomer(replys);
            buildReplyComment(allCommentsList, comment, replys);
            commentList2.add(comment);
        }
        return commentList2;
    }

    /**
     * 构建评论与回复评论的关系，此处使用递归
     *
     * @param comments 关于这篇文章所有评论
     * @param comment  根评论
     * @param replys   回复这条评论的集合
     */
    private void buildReplyComment(List<Comments> comments, Comment comment, List<Comment> replys) {
        //查询此条评论的子回复们
        List<Comment> replyComments = findCommentListByCommentId(comments, comment.getId());
        replys.addAll(replyComments); // 把所有的回复添加到评论实例化的回复集合中
        for (Comment c : replyComments) { // 遍历回复中的回复
            c.setReply_name(comment.getAuthor_name());
            c.setReply_url(comment.getAuthor_url());
            buildReplyComment(comments, c, replys); // 递归调用
        }
    }

    /**
     * 为了不频繁查询数据库，所以在内存中遍历查找
     *
     * @param comments  数据库返回原始数据
     * @param CommentID 查找的评论ID
     * @return
     */
    private List<Comment> findCommentListByCommentId(List<Comments> comments, Long CommentID) {
        List<Comment> commentList = new ArrayList<Comment>();
        for (Comments comment : comments) {
            if (comment.getParent_id() == CommentID) {
                Comment comment1 = new Comment();
                comment1.convertFromComments(comment);
                commentList.add(comment1);
            }
        }
        return commentList;
    }

    /**
     * 查询根评论，为了不频繁查询数据库，所以在内存中遍历查找
     *
     * @param comments
     * @return
     */
    private List<Comments> findRootComments(List<Comments> comments) {
        List<Comments> commenstList = new ArrayList<Comments>();
        for (Comments comment : comments) {
            if (comment.getParent_id() == 1) {
                commenstList.add(comment);
            }
        }
        return commenstList;
    }

    /**
     * 评论审核线程
     */
    private class FirstCommentAuditThread extends Thread {
        private Comment comment;

        FirstCommentAuditThread(Comment comment) {
            this.comment = comment;
        }

        @Override
        public void run() {
            //检查内容合法性
            AliyunGreenWebUtil aliyunGreenWebUtil = new AliyunGreenWebUtil();
            List<String> contents = new ArrayList<String>();
            String commentContent = comment.getComment_content();
            if (commentContent.length() > 3999) {
                while (commentContent.length() > 3999) {
                    contents.add(commentContent.substring(0, 3999));
                    commentContent = commentContent.substring(3999, commentContent.length() - 1);
                }
            } else {
                contents.add(commentContent);
            }
            boolean greenWebContent = true;
            String greenWeb_type = "";
            for (String content : contents) {
                List<AliyunGreenWeb> aliyunGreenWebs = aliyunGreenWebUtil.TextScanSample(content);
                for (AliyunGreenWeb obj : aliyunGreenWebs) {
                    if (obj.getSuggestion().equals("block")) {
                        //命中检测
                        greenWebContent = false;
                        greenWeb_type = obj.getLabelZh();
                    }
                }
            }
            if (!greenWebContent) {
                //发现违法信息
                sendEmail(comment, "关于NEILRE.NCOM的文章评论审核通知",
                        "文章评论内容自动审查未通过的通知：文章评论自动审查；内容检测到非法内容，评论被隐藏",
                        "<p style=\"margin: 0;text-indent: 2em;\">很遗憾的通知您，您在NEILREN.COM的文章评论自动审查暂时未能通过，未能通过的原因是：</p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\">在您留言的内容中检测到非法内容，类型为【" + greenWeb_type +
                                "】，您的评论被自动隐藏。</p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\">您评论的文章链接：https://www.neilren.com/Article/" + comment.getArticle_id() + "</p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\">以下为您的评论:</p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\">" + comment.getComment_content() + "</p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\"></p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 mail@neilren.com 进行人工处理。</p>");
                return;
            } else {
                //检查称呼中的信息
                List<String> names = new ArrayList<String>();
                String commentName = comment.getAuthor_name();
                if (commentName.length() > 3999) {
                    while (commentName.length() > 3999) {
                        names.add(commentName.substring(0, 3999));
                        commentName = commentName.substring(3999, commentName.length() - 1);
                    }
                } else {
                    names.add(commentName);
                }
                for (String name : names) {
                    List<AliyunGreenWeb> aliyunGreenWebs = aliyunGreenWebUtil.TextScanSample(name);
                    for (AliyunGreenWeb obj : aliyunGreenWebs) {
                        if (obj.getSuggestion().equals("block")) {
                            //命中检测
                            greenWebContent = false;
                            greenWeb_type = obj.getLabelZh();
                        }
                    }
                }
                if (greenWebContent) {
                    //审核通过
                    comment.setApproved(1);
                    commentsDao.update(comment);
                } else {
                    //发现违法信息
                    sendEmail(comment, "关于NEILRE.NCOM的文章评论审核通知",
                            "文章评论内容自动审查未通过的通知：文章评论自动审查；称呼中检测到非法内容，评论被隐藏",
                            "<p style=\"margin: 0;text-indent: 2em;\">很遗憾的通知您，您在NEILREN.COM的文章评论自动审查暂时未能通过，未能通过的原因是：</p>" +
                                    "<p style=\"margin: 0;text-indent: 2em;\">在您留言的称呼中检测到非法内容，类型为【" + greenWeb_type +
                                    "】，您的评论被自动隐藏。</p>" +
                                    "<p style=\"margin: 0;text-indent: 2em;\">您评论的文章链接：https://www.neilren.com/Article/" + comment.getArticle_id() + "</p>" +
                                    "<p style=\"margin: 0;text-indent: 2em;\">以下为您的评论时使用的称呼：</p>" +
                                    "<p style=\"margin: 0;text-indent: 2em;\">" + comment.getAuthor_name() + "</p>" +
                                    "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 mail@neilren.com 进行人工处理。</p>");
                }
            }
        }
    }

    private void sendEmail(Comment comment, String title, String Sabstract, String content) {
        EmailObject emailObject = new EmailObject();
        emailObject.setName(comment.getAuthor_name());
        emailObject.setToEmail(comment.getAuthor_email());
        emailObject.setEmailSubject(title);
        emailObject.setEvent("文章评论自动审查事件");
        emailObject.setSecurityrating("非涉密内容");
        emailObject.setSabstract(Sabstract);
        emailObject.setEmailContent(content);
        try {
            emailUtils.sendHtmlEmail(emailObject);
        } catch (Exception e) {

        }
    }
}
