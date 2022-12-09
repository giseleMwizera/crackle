package com.devops.crackle.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/movie")
public class MovieController {
    public final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getAllMovies();
    }
    @PostMapping
    public void registerMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
    }
    @DeleteMapping(path = "{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId){
        movieService.deleteMovie(movieId);
    }
}
