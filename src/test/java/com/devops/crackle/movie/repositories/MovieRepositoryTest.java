package com.devops.crackle.movie.repositories;

import com.devops.crackle.movie.Movie;
import com.devops.crackle.movie.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MovieRepositoryTest {
    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
    @Autowired
    private MovieRepository underTest;

    @Test
    void itShouldCheckIfMovieOfGivenTitleExists() {
        // given
        String title = "manifest";
        Movie movie = new Movie(1L,title,"A mysterious movie", LocalDate.of(2022, Month.APRIL,02), Collections.emptyList());
        underTest.save(movie);

        // When
        Optional<Movie> exists = underTest.findByTitle(title);

        //Then
        assertThat(exists).isNotEmpty();
    }

    @Test
    void itShouldCheckIfMovieOfGivenTitleDoesNotExist(){
            //        given
        String title = "manifest";
            //        When
        Optional<Movie> exists = underTest.findByTitle(title);
            //        Then
        assertThat(exists).isEmpty();
    }
}