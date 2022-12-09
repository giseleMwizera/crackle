package com.devops.crackle.author.services;

import com.devops.crackle.author.Author;
import com.devops.crackle.author.AuthorRepository;
import com.devops.crackle.author.AuthorService;
import com.devops.crackle.movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    private AuthorService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AuthorService(authorRepository);
    }

    @Test
    void canGetAllAuthors() {
        //when
        underTest.getAuthors();
        //then
        verify(authorRepository).findAll();
    }

    @Test
    void canAddAuthor() {
        //given
        Author author = new Author("gisele", "gisele@gmail.com", LocalDate.of(2000, Month.APRIL, 23),12, Collections.emptyList());

        //when
        underTest.addAuthor(author);

        //then
        ArgumentCaptor<Author> authorArgumentCaptor = ArgumentCaptor.forClass(Author.class);
        verify(authorRepository).save(authorArgumentCaptor.capture());

        Author authorCaptured = authorArgumentCaptor.getValue();
        assertThat(authorCaptured).isEqualTo(author);


    }

    @Test
    @Disabled
    void deleteAuthor() {
    }

    @Test
    @Disabled
    void assignBookToAuthor() {
    }
}