package com.devops.crackle.movie;

import com.devops.crackle.author.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "MOVIE")
public class Movie {
    @Id
    @Column(name="movie_id")
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String title;
    private String summary;
    private LocalDate releaseDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private Collection<Author> authors;

    public Movie() {
    }
    public Movie(Long id, String title, String summary, LocalDate releaseDate, Collection<Author> authors) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.authors = authors;
    }

    public Movie(String title, String summary, LocalDate releaseDate, Collection<Author> authors) {
        this.title = title;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.authors = authors;
    }

}
