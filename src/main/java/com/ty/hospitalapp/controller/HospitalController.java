package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.Hospital;
import com.ty.hospitalapp.service.HospitalService;
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
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hospital save(@Valid @RequestBody Hospital hospital) {
        return service.save(hospital);
    }

    @GetMapping
    public List<Hospital> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Hospital get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Hospital update(@PathVariable("id") int id, @Valid @RequestBody Hospital hospital) {
        return service.update(id, hospital);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
