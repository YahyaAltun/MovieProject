package com.examplemovie.controller;

import com.examplemovie.domain.Comment;
import com.examplemovie.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private ICommentService commentService;

   //http://localhost:8081/comment/getByMovieId/1
    @GetMapping("/getByMovieId/{id}")
    public ResponseEntity<Set<Comment>> getByMovie(@PathVariable("id") Integer movieId) {
        Set<Comment> comments = commentService.findCommentByMovie(movieId);
        return ResponseEntity.ok(comments);
    }

    //http://localhost:8081/comment/create/1/1
    @PostMapping("/create/{uId}/{mId}")
    @PreAuthorize("hasRole('ROLE_ONEMONTH') or hasRole('ROLE_THREEMONTH') or hasRole('ROLE_SİXMONTH') or hasRole('ROLE_ONEYEAR')")
    public ResponseEntity<Map<String, String>> createComment( @PathVariable("uId") Long userId,@PathVariable("mId") Integer movieId, @RequestBody Comment comment) {
        commentService.createComment(userId, movieId, comment);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Comment created successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //http://localhost:8081/comment/delete/1/1/3
    @DeleteMapping("/delete/{uId}/{mId}/{cId}")
    @PreAuthorize("hasRole('ROLE_ONEMONTH') or hasRole('ROLE_THREEMONTH') or hasRole('ROLE_SİXMONTH') or hasRole('ROLE_ONEYEAR')")
    public ResponseEntity<Map<String,String>> deleteComment(@PathVariable("uId") Long userId,@PathVariable("mId") Integer movieId,@PathVariable("cId") Long commentId) {
        commentService.deleteComment(userId, movieId, commentId);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Comment deleted successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}