package com.devops.crackle.movie.services;

import com.devops.crackle.movie.Movie;
import com.devops.crackle.movie.MovieService;
import com.devops.crackle.movie.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
   @Mock
   private MovieRepository movieRepository;

    private MovieService underTest;

    @BeforeEach
    void setUp() {
        underTest = new MovieService(movieRepository);
    }

    @Test
    void canGetAllMovies() {
//        when
        underTest.getAllMovies();
//        then
        verify(movieRepository).findAll();
    }

    @Test
    void throwErrorIfMovieExistsByTitle(){
        // given
        Movie movie = new Movie(1L,"Manifest","A mysterious movie",
                LocalDate.of(2022, Month.APRIL,02), Collections.emptyList());


        given(movieRepository.findByTitle(movie.getTitle()))
                .willReturn(Optional.of(movie));


        //when
        //then
        assertThatThrownBy(()-> underTest.addMovie(movie)).isInstanceOf(IllegalStateException.class)
             .hasMessageContaining("Movie already exists");
        verify(movieRepository, never()).save(any());
    }
    @Test
    void canAddMovie() {
        // given
        Movie movie = new Movie(1L,"Manifest","A mysterious movie",
                LocalDate.of(2022, Month.APRIL,02), Collections.emptyList());

        // when
        underTest.addMovie(movie);

        // then
        ArgumentCaptor<Movie> movieArgumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(movieRepository).save(movieArgumentCaptor.capture());

        Movie movieCaptured = movieArgumentCaptor.getValue();

        assertThat(movieCaptured).isEqualTo(movie);


    }

    @Test
    void canDeleteMovie() {
        // given
        Movie movie = new Movie(1L,"Manifest","A mysterious movie",
                LocalDate.of(2022, Month.APRIL,02), Collections.emptyList());

        given(movieRepository.existsById(any())).willReturn(true);

        // when
        underTest.deleteMovie(1L);
        //then
        assertThat(movieRepository.findById(any())).isEmpty();

    }
}