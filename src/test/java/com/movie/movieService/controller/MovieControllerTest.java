package com.movie.movieService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.movieService.entity.Movie;
import com.movie.movieService.repository.MovieRepository;
import com.movie.movieService.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {
//real one
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Movie movie;

    List<Movie> movies;

    @BeforeEach
    void setUp(){
        movie = new Movie("100", "test", 10.00);
        movies = Arrays.asList(
                new Movie("100", "test", 10.00),
                new Movie("100", "test", 10.00));
    }

    @Test
    void shouldAddMovieAndReturnOk() throws Exception {
        mockMvc.perform(post("/addMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllMovies() throws Exception {

        Mockito.when(movieService.getMovies()).thenReturn(movies);
        mockMvc.perform(MockMvcRequestBuilders.get("/Movies"))
                .andExpect(status().isOk());
    }

    @Test
        //@Disabled
    void deleteMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", 1))
                .andExpect(status().isOk());
    }


}