package com.example.project_library.service;
import com.example.project_library.entity.Book;
import com.example.project_library.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> listAll();
    void deletePersonById(Long personId);
    Person updatedPerson(Long id,Person updatedPerson);
    Person saveNewPerson(Person person);
    Optional<Person> getById(Long personId);



}
