package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.Address;
import com.ty.hospitalapp.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressService {

    private final AddressRepository repo;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    public List<Address> getAll() {
        return repo.findAll();
    }

    public Address get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Address " + id + " not found"));
    }

    public Address update(int id, Address data) {
        Address old = get(id);
        old.setStreet(data.getStreet());
        old.setState(data.getState());
        old.setCountry(data.getCountry());
        old.setPin(data.getPin());
        return repo.save(old);
    }
}
