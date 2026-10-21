package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.Address;
import com.ty.hospitalapp.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public List<Address> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Address get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable("id") int id, @RequestBody Address address) {
        return service.update(id, address);
    }
}
