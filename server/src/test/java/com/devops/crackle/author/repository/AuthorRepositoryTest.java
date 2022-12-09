package com.devops.crackle.author.repository;

import com.devops.crackle.author.Author;
import com.devops.crackle.author.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Autowired
    public AuthorRepository underTest;


    @Test
    void itShouldCheckIfAuthorByEmailExists() {
        // given
        String email = "gisele@gmail.com";
        Author author = new Author("gisele", email, LocalDate.of(2000, Month.APRIL, 23),12, Collections.emptyList());
        underTest.save(author);

        // when
        Optional<Author> authorExists = underTest.findByEmail(email);

        // then
        assertThat(authorExists).isNotEmpty();
    }
    @Test
    void itShouldCheckIfAuthorByEmailDoesNotExist() {
        // given
        String email = "gisele@gmail.com";

        // when
        Optional<Author> authorExists = underTest.findByEmail(email);

        // then
        assertThat(authorExists).isEmpty();
    }
}