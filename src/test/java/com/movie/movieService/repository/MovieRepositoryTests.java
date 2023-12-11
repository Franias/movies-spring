package com.movie.movieService.repository;

import com.movie.movieService.entity.Movie;
import com.movie.movieService.exceptions.MovieException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class MovieRepositoryTests {

	@Autowired
	private MovieRepository movieRepository;
	private Movie movie;

	@BeforeEach
	void setUp(){
		movie = new Movie("test", "test", 10.00);
	}

	@Test
	void shouldSaveAMovie() throws MovieException {

		//given instantiate
		//I do that in the setUp Method

		//when save a movie
		Movie movie1 = movieRepository.save(movie);
		System.out.println(movie1);
		//then it should return a movie
		Assert.notNull(movie1);

		//given I have a valid movie
		Optional<Movie> movieOptional = movieRepository.findById(movie1.getId());
		System.out.println(movieOptional.get());
		//when delete the movie
		movieRepository.deleteById(movieOptional.get().getId());
	}

	@Test
	void shouldGetAMovieById() throws MovieException {

		//given save a movie
		Movie movie1 = movieRepository.save(movie);
		System.out.println(movie1);
		Assert.notNull(movie1);

		//when get a movie by id
		Movie movieFromDB = movieRepository.getById(movie1.getId());
		System.out.println(movieFromDB);

		//then it should return a movie
		Assertions.assertEquals(movieFromDB, movie1);
		movieRepository.deleteById(movieFromDB.getId());
	}

}
