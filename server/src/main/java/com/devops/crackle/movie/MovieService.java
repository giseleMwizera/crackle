package com.devops.crackle.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    public final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }
    public void addMovie(Movie movie) {
        Optional<Movie> movieByEmail = movieRepository.findByTitle(movie.getTitle());
        if(movieByEmail.isPresent()){
            throw  new IllegalStateException("Movie already exists");
        }

        movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovie(Long movieId) {
        boolean movieExists = movieRepository.existsById(movieId);
        if(!movieExists){
            throw new IllegalStateException("Movie doesn't exist");
        }
        movieRepository.deleteById(movieId);
    }
}
