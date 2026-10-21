package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.Person;
import com.ty.hospitalapp.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@Valid @RequestBody Person person) {
        return service.save(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable("id") int id, @Valid @RequestBody Person person) {
        return service.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping("/gender/{gender}")
    public List<Person> getByGender(@PathVariable("gender") String gender) {
        return service.getByGender(gender);
    }

    @GetMapping("/age/{age}")
    public List<Person> getByAge(@PathVariable("age") int age) {
        return service.getByAge(age);
    }

    @GetMapping("/phone/{phone}")
    public List<Person> getByPhone(@PathVariable("phone") long phone) {
        return service.getByPhone(phone);
    }
}
