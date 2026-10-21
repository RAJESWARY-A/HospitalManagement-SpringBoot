package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.User;
import com.ty.hospitalapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User save(User data) {
        return repo.save(data);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found"));
    }

    public User update(int id, User data) {
        User old = get(id);
        old.setName(data.getName());
        old.setEmail(data.getEmail());
        old.setPhone(data.getPhone());
        old.setRole(data.getRole());
        if (data.getPassword() != null && !data.getPassword().isBlank()) {
            old.setPassword(data.getPassword());
        }
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }

    public List<User> getByName(String name) {
        return repo.findByName(name);
    }

    public List<User> getByRole(String role) {
        return repo.findByRole(role);
    }
}
