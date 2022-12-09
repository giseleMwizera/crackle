package com.devops.crackle.author;

import com.devops.crackle.movie.Movie;
import com.devops.crackle.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
     public final AuthorRepository authorRepository;

 @Autowired
    public AuthorService(@Lazy AuthorRepository authorRepository){
     this.authorRepository = authorRepository;
 }
@Autowired
public MovieRepository movieRepository;

 public List<Author> getAuthors() {
     return authorRepository.findAll();
 }

public void addAuthor(Author author) {
     Optional<Author> authorByEmail = authorRepository.findByEmail(author.getEmail());
           if(authorByEmail.isPresent()){
                throw new IllegalStateException("Author exists");
            }

            authorRepository.save(author);
    }


    public void deleteAuthor(Long authorId) {
     boolean authorExists = authorRepository.existsById(authorId);
     if(!authorExists){
         throw new IllegalStateException("Author doesn't exist");
     }
      authorRepository.deleteById(authorId);
    }

    public void assignBookToAuthor(Long authorId, Long movieId) {
        Collection<Movie> assignedMovies = null;
      Author author = authorRepository.findById(authorId).get();
      Movie movie = movieRepository.findById(movieId).get();
      assignedMovies = author.getMovies();
      assignedMovies.add(movie);
      author.setMovies(assignedMovies);
      authorRepository.save(author);

    }
}
