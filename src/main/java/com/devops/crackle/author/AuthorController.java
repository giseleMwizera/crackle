package com.devops.crackle.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/author")
public class AuthorController {

    public final AuthorService authorService;

    @Autowired
public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @PostMapping
     public void registerAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
    }
    @DeleteMapping(path = "{authorId}")
    public void deleteAuthor(@PathVariable("authorId") Long authorId){
        authorService.deleteAuthor(authorId);
    }
    @PutMapping(path = "{authorId}/movie/{movieId}")
    public void assignBookToAuthor(@PathVariable("authorId") Long authorId,@PathVariable("movieId") Long movieId){
        authorService.assignBookToAuthor(authorId,movieId);
    }




}
