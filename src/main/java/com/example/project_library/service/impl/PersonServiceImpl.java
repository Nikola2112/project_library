package com.example.project_library.service.impl;

import com.example.project_library.entity.Book;
import com.example.project_library.entity.Person;
import com.example.project_library.repo.PersonRepo;
import com.example.project_library.service.PersonService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PersonServiceImpl implements PersonService {
    public PersonRepo personRepo;

    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public List<Person> listAll() {
        return (List<Person>) personRepo.findAll();
    }

    @Override
    public void deletePersonById(Long id) {
        personRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Person updatedPerson(Long id, Person updatedPerson){
        Person existingPerson = getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        existingPerson.setName(updatedPerson.getName());
        existingPerson.setSurname(updatedPerson.getSurname());
        existingPerson.setYear(updatedPerson.getYear());
         return saveNewPerson(existingPerson);
    }

    @Override
    public Person saveNewPerson(Person person) {
        return personRepo.save(person);
    }

    @Override
    public Optional<Person> getById(Long personId) {
        return personRepo.findById(personId);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = personRepo.findById((long) id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBorrowedBooks());

            person.get().getBorrowedBooks().forEach(book -> {
                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                //10 суток
                if (diffInMillies > 864000000)
                    book.setExpired(true);
            });

            return person.get().getBorrowedBooks();
        }
        else {
            return Collections.emptyList();
        }
    }
}
