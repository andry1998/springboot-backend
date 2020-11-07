package com.example.test404.controllers;

import com.example.test404.exception.ResourceNotFoundException;
import com.example.test404.models.Person;
import com.example.test404.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://demo1-angular.s3-website.eu-central-1.amazonaws.com")
@RestController
public class PersonController {

//    @RequestMapping("/persons")
//    public String hi() {
//        return "persons-w";
//    }
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not exist with id:" + id));
        return ResponseEntity.ok(person);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable long id, @RequestBody Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not exist with id:" + id));
        person.setName(personDetails.getName());
        person.setSurname(personDetails.getSurname());
        person.setEmail(personDetails.getEmail());
        person.setPhone(personDetails.getPhone());

        Person updatePerson = personRepository.save(person);
        return ResponseEntity.ok(updatePerson);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePerson(@PathVariable long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not exist with id :" + id));
        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
