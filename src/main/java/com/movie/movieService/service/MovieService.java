package com.movie.movieService.service;

import com.movie.movieService.entity.Movie;
import com.movie.movieService.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie){
       return movieRepository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies){
        return movieRepository.saveAll(movies);
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public void deleteMovie(int id) {
            movieRepository.deleteById(id);
    }

    public Optional<Movie> getMovie(int id){
        return movieRepository.findById(id);
    }

}
