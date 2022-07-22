package com.examplemovie.controller;

import com.examplemovie.domain.Movie;
import com.examplemovie.service.MovieServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieServiceImpl movieService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movie = movieService.findAll();
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<Set<Movie>> getMovieByUser(@PathVariable("id") Long userId) {
        Set<Movie> movies = movieService.findMovieByUser(userId);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Map<String, String>> createMovie(@Valid @PathVariable("id") Long userId, @RequestBody Movie movie) {
        movieService.createMovie(userId, movie);
        Map<String, String> map = new HashMap<>();
        map.put("message", "User created successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteMovie(@PathVariable("id") Integer movieId,@RequestBody Long userId) {
        movieService.deleteMovie(movieId, userId);
        Map<String, String> map = new HashMap<>();
        map.put("message", "User deleted successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
