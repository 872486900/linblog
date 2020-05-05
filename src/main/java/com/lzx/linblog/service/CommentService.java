package com.lzx.linblog.service;

import com.lzx.linblog.po.Comment;

import java.util.List;

/**
 * Created by 87248 on 2020-04-21 11:24
 */
public interface CommentService {
    //获取评论列表
    List<Comment> listCommentByBlogId(Long blogId);
    //保存用户评论
    Comment saveComment(Comment comment);
}
