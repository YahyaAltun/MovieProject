package com.examplemovie.service;

import com.examplemovie.domain.Comment;
import com.examplemovie.domain.Movie;
import com.examplemovie.domain.User;
import com.examplemovie.exception.ResourceNotFoundException;
import com.examplemovie.repository.ICommentRepository;
import com.examplemovie.repository.IMovieRepository;
import com.examplemovie.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
//@AllArgsConstructor
public class CommentServiceImpl implements ICommentService{

    @Autowired
    private IMovieRepository iMovieRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private ICommentRepository iCommentRepository;

    @Override
    public void createComment(Long userId, Integer movieId, Comment comment) {
        User user=iUserRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found id"+userId));
        Movie movie=iMovieRepository.findById(movieId).orElseThrow(()->new ResourceNotFoundException("Movie not found id"+movieId));
        comment.setMovieComment(movie);
        comment.setUserComment(user);
        iCommentRepository.save(comment);
    }

    @Override
    public Set<Comment> findCommentByMovie(Integer movieId) {
        Movie movie=iMovieRepository.findById(movieId).orElseThrow(()->new ResourceNotFoundException("Movie not found id"+movieId));
        Set<Comment> comments = movie.getComment();
        return comments;
    }

    @Override
    public void deleteComment(Long userId, Integer movieId, Long commentId) {
        iUserRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User not found id"+userId));
        Movie movie=iMovieRepository.findById(movieId).orElseThrow(()->
                new ResourceNotFoundException("Movie not found id"+movieId));
        Comment comment = iCommentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment not found id"+commentId));
        if (!comment.getUserComment().getId().equals(userId)){
            throw new ResourceNotFoundException("You can not delete this movie");
        }
        iCommentRepository.delete(comment);
    }
}