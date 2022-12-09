package com.devops.crackle.movie.controller;

import com.devops.crackle.movie.Movie;
import com.devops.crackle.movie.MovieService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;


    @Test
    void itShouldGetAllMovies() throws Exception {
        List<Movie> movies = movieService.getAllMovies();
        when(movieService.getAllMovies()).thenReturn(movies);

        mockMvc.perform((RequestBuilder) get("/movie")).andExpect(status().isOk());
    }

    @Test
    @Disabled
    void registerMovie() {
    }

    @Test
    @Disabled
    void deleteMovie() {
    }
}