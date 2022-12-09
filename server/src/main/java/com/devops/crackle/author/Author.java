package com.devops.crackle.author;

import com.devops.crackle.movie.Movie;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table
public class Author {
    @Id
    @Column(name="author_id")
    @SequenceGenerator(
            name="author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    private String email;
    private LocalDate dob;

    private int age;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_movie",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name="movie_id")

    )
    private Collection<Movie> movies;

    public Author() {
    }

    public Author(Long id, String name, String email, LocalDate dob, int age, Collection<Movie> movies) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.movies = movies;
    }

    public Author(String name, String email, LocalDate dob, int age, Collection<Movie> movies) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }
}
