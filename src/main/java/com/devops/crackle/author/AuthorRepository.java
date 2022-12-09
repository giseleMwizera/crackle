package com.devops.crackle.author;

import com.devops.crackle.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByEmail(String email);
}
