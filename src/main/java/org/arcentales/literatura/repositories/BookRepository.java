package org.arcentales.literatura.repositories;

import org.arcentales.literatura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor_NameIgnoreCase(String name);

    List<Book> findByTitleContainingIgnoreCase(String title);
}