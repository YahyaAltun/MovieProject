package com.examplemovie.service;

import com.examplemovie.domain.Comment;

import java.util.Set;

public interface ICommentService {

    void createComment(Long userId,Integer movieId, Comment comment);

    Set<Comment> findCommentByMovie(Integer movieId);

    void deleteComment(Long userId,Integer movieId, Long commentId);
}