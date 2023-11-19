package com.example.project_library.controller;

import com.example.project_library.entity.Book;
import com.example.project_library.entity.Person;
import com.example.project_library.service.BookService;
import com.example.project_library.service.PersonService;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibraryController  {
    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public LibraryController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {

        List<Person> people = personService.listAll();
        List<Book> availableBooks = bookService.findAvailableBooks();


        model.addAttribute("people", people);
        model.addAttribute("availableBooks", availableBooks);


        return "main-page";
    }
    @GetMapping("/assign")
    public String showAssignPage(Model model) {
        model.addAttribute("availableBooks", bookService.findAvailableBooks());
        model.addAttribute("people", personService.listAll());
        return "assign-page";
    }


    @PostMapping("/assign")
    public String assignBookToPerson(@RequestParam Long bookId, @RequestParam Long personId) {

        Book book = bookService.getById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));

        Person person = personService.getById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + personId));


        book.setBorrower(person);
        bookService.saveNewBook(book);


        return "redirect:/";
    }
}

