package com.movie.movieService.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movie.movieService.entity.Movie;
import com.movie.movieService.exceptions.MovieException;
import com.movie.movieService.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @PostMapping("/addMultipleMovies")
    public List<Movie> addMultipleMovies(@RequestBody List<Movie> movies){
        return movieService.saveMovies(movies);
    }

    @JsonFormat
    @GetMapping("/Movies")
    public List<Movie> getAllMovies(){
        return movieService.getMovies();
    }


    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable(value = "id") int id) throws MovieException {
        movieService.deleteMovie(id);
    }

    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable(value = "id") int id) {
        return movieService.getMovie(id).get();
    }

    @GetMapping("/desconto/{id}")
    @JsonFormat
    public Double getDesconto(@PathVariable(value = "id") int id) {

        Movie movie = movieService.getMovie(id).get();
        System.out.println(movie.toString());
        var template = new RestTemplate();

        ResponseEntity<Double> responseEntity = template.getForEntity("http://localhost:8082/api/desconto/" + movie.getValor(), Double.class);

        Double valorComDescontoAplicado = responseEntity.getBody();

        return valorComDescontoAplicado;
    }
}
