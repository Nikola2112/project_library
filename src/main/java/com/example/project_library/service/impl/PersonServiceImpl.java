package com.example.project_library.service.impl;

import com.example.project_library.entity.Person;
import com.example.project_library.repo.PersonRepo;
import com.example.project_library.service.PersonService;
import org.springframework.stereotype.Service;

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
    public Person saveNewPerson(Person person) {
        return personRepo.save(person);
    }

    @Override
    public Optional<Person> getById(Long personId) {
        return personRepo.findById(personId);
    }
}
