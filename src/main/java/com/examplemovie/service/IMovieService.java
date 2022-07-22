package com.examplemovie.service;

import com.examplemovie.domain.Movie;

import java.util.List;
import java.util.Set;

public interface IMovieService {
    List<Movie> findAll();

    Set<Movie> findMovieByUser(Long userId);

    void createMovie(Long userId,Movie movie);

    void deleteMovie(Integer movieId, Long userId);
}
