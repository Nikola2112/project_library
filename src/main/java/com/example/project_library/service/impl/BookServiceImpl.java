package com.example.project_library.service.impl;

import com.example.project_library.entity.Book;
import com.example.project_library.entity.Person;
import com.example.project_library.repo.BookRepo;
import com.example.project_library.service.BookService;
import org.hibernate.Hibernate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {

    public BookRepo bookrepo;

    public BookServiceImpl(BookRepo bookrepo) {
        this.bookrepo = bookrepo;
    }
    @Override
    public List<Book> listAll() {
        return  (List<Book>) bookrepo.findAll();
    }
    public void deleteBookById(Long id) {
        bookrepo.deleteById(id);
    }
    @Override
    public Book saveNewBook(Book book) {
        return bookrepo.save(book);
    }
    @Override
    public Optional<Book> getById(Long bookId) {
        return bookrepo.findById(bookId);
    }

    @Override
    public List<Book> findAvailableBooks() {
        return bookrepo.findByBorrowerIsNull();
    }
    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return bookrepo.findAll(Sort.by("year"));
        else
            return bookrepo.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return bookrepo.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else
            return bookrepo.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
      public List<Book> searchByName(String query) {
        return bookrepo.findByNameStartingWith(query);
    }
}
