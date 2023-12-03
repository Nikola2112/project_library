package com.example.project_library.service;

import com.example.project_library.entity.Book;
import com.example.project_library.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    void deleteBookById(Long bookId);
    Book saveNewBook(Book book);
    Optional<Book> getById(Long bookId);

    List<Book> findAvailableBooks();
    List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear);
    List<Book> findAll(boolean sortByYear);
    List<Book> searchByName(String query);
}
