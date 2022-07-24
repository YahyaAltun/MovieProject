package com.examplemovie.service;

import com.examplemovie.domain.Movie;
import com.examplemovie.domain.User;
import com.examplemovie.dto.MovieDTO;
import com.examplemovie.exception.ResourceNotFoundException;
import com.examplemovie.repository.IMovieRepository;
import com.examplemovie.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements IMovieService{

    private IMovieRepository iMovieRepository;

    private IUserRepository iUserRepository;

    @Override
    public List<Movie> findAll() {
        return iMovieRepository.findAll();
    }

    @Override
    public Set<Movie> findMovieByUser(Long userId) {
        User user=iUserRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found id"+userId));
        Set<Movie> movies = user.getMovie();
        return movies;
    }

    @Override
    public void createMovie(Long userId, Movie movie) {
        User user=iUserRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found id"+userId));
        movie.setCreatedForUser(user);
        iMovieRepository.save(movie);


    }

    @Override
    public void deleteMovie(Integer movieId,Long userId) {
        iUserRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User not found id"+userId));
        Movie movie=iMovieRepository.findById(movieId).orElseThrow(()->
                new ResourceNotFoundException("Movie not found id"+movieId));
        if (!movie.getCreatedForUser().getId().equals(userId)){
            throw new ResourceNotFoundException("You can not delete this movie");
        }
        iMovieRepository.delete(movie);

    }


}
