package com.example.project_library.controller;

import com.example.project_library.entity.Book;
import com.example.project_library.entity.Person;
import com.example.project_library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String listPeople(Model model) {
        model.addAttribute("people", personService.listAll());
        return "main-page-of-people";
    }

    @GetMapping("/new")
    public String showPersonForm(Model model) {
        model.addAttribute("person", new Person());
         return "page-new-people";
    }

    @PostMapping("/new")
    public String savePerson(@ModelAttribute Person person) {
        personService.saveNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable Long id, Model model) {
        Person person = personService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        model.addAttribute("person", person);
        return "page-show-person";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Person person = personService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        model.addAttribute("person", person);
        return "page-edit-person";
    }

    @PostMapping("/{id}/edit")
    public String updatePerson(@PathVariable Long id, @ModelAttribute Person updatedPerson, Model model) {
        Person updatedPersonResult = personService.updatedPerson(id, updatedPerson);
        model.addAttribute("updatedPerson", updatedPersonResult);
        model.addAttribute("message", "Person updated successfully");
        return "redirect:/people";
    }

    @GetMapping("/{id}/delete")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return "redirect:/people";
    }
}

