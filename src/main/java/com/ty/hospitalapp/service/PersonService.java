package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.Person;
import com.ty.hospitalapp.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonService {

    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    public Person save(Person data) {
        return repo.save(data);
    }

    public List<Person> getAll() {
        return repo.findAll();
    }

    public Person get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Person " + id + " not found"));
    }

    public Person update(int id, Person data) {
        Person old = get(id);
        old.setName(data.getName());
        old.setAge(data.getAge());
        old.setGender(data.getGender());
        old.setPhone(data.getPhone());
        old.setDob(data.getDob());
        old.setEmail(data.getEmail());
        old.setAddress(data.getAddress());
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }

    public List<Person> getByGender(String gender) {
        return repo.findByGender(gender);
    }

    public List<Person> getByAge(int age) {
        return repo.findByAge(age);
    }

    public List<Person> getByPhone(long phone) {
        return repo.findByPhone(phone);
    }
}
