package com.example.project_library.service.impl;

import com.example.project_library.entity.Book;
import com.example.project_library.entity.Person;
import com.example.project_library.repo.BookRepo;
import com.example.project_library.service.BookService;
import org.springframework.stereotype.Service;

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
}
