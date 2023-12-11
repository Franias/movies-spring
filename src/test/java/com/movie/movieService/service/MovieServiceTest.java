package com.movie.movieService.service;

import com.movie.movieService.entity.Movie;
import com.movie.movieService.exceptions.MovieException;
import com.movie.movieService.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    private AutoCloseable autoCloseable;
    private MovieService movieService;
    private Movie movie;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        movieService = new MovieService(movieRepository);
        movie = new Movie("test", "test", 10.00);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveMovie() {
        //Verify if the repository is called when I use the SaveFair Method
        //also checks if the save method is called with the correct argument

        movieService.saveMovie(movie);
        ArgumentCaptor<Movie> fairArgumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(movieRepository).save(fairArgumentCaptor.capture());

        Movie captureMovie = fairArgumentCaptor.getValue();

        assertThat(captureMovie).isEqualTo(movie);
    }

    @Test
    void deleteMovie() throws MovieException {
        movieService.deleteMovie(1);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(movieRepository).deleteById(argumentCaptor.capture());

        int capturedArgument = argumentCaptor.getValue();
        assertThat(capturedArgument).isEqualTo(1);
    }



    @Test
    void getFair() {
        movieService.getMovie(1);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(movieRepository).findById(argumentCaptor.capture());

        int capturedArgument = argumentCaptor.getValue();
        assertThat(capturedArgument).isEqualTo(1);
    }

    @Test
    void getAllFairs() {
        movieService.getMovies();
        verify(movieRepository).findAll();

    }
}