package com.example.project_library.repo;

import com.example.project_library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    List<Book> findByBorrowerIsNull();
    List<Book> findByNameStartingWith(String name);
}
