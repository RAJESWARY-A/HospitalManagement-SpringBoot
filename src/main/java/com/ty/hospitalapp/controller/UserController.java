package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.User;
import com.ty.hospitalapp.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@Valid @RequestBody User user) {
        return service.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") int id, @Valid @RequestBody User user) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping("/name/{name}")
    public List<User> getByName(@PathVariable("name") String name) {
        return service.getByName(name);
    }

    @GetMapping("/role/{role}")
    public List<User> getByRole(@PathVariable("role") String role) {
        return service.getByRole(role);
    }
}
