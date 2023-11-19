package com.example.project_library.controller;

import com.example.project_library.entity.Book;
import com.example.project_library.service.BookService;
import com.example.project_library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.listAll());
        return "main-page-of-books";
    }

    @GetMapping("/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("people", personService.listAll());
        return "page-new-book";
    }

    @PostMapping("/new")
    public String saveBook(@ModelAttribute Book book) {
        bookService.saveNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "page-show-book";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("people", personService.listAll());
        return "page-edit-book";
    }

    @PostMapping("/{id}/edit")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        Book existingBook = bookService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setYear(book.getYear());
        existingBook.setBorrower(book.getBorrower());
        bookService.saveNewBook(existingBook);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}

