package com.example.project_library.service;

import com.example.project_library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();
    void deleteBookById(Long bookId);
    Book saveNewBook(Book book);
    Optional<Book> getById(Long bookId);
    Book updatedBook(Long id, Book updatedBook);
    List<Book> findAvailableBooks();
    List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear);
    List<Book> findAll(boolean sortByYear);
    List<Book> searchByName(String query);
}
