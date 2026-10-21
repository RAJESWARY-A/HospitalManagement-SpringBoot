package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.Encounter;
import com.ty.hospitalapp.service.EncounterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncounterController {

    private final EncounterService service;

    public EncounterController(EncounterService service) {
        this.service = service;
    }

    @PostMapping("/encounters")
    @ResponseStatus(HttpStatus.CREATED)
    public Encounter save(@RequestParam("branchId") int branchId, @RequestParam("personId") int personId, @RequestBody Encounter encounter) {
        return service.save(branchId, personId, encounter);
    }

    @GetMapping("/encounters")
    public List<Encounter> getAll() {
        return service.getAll();
    }

    @GetMapping("/encounters/{id}")
    public Encounter get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/encounters/{id}")
    public Encounter update(@PathVariable("id") int id, @RequestBody Encounter encounter) {
        return service.update(id, encounter);
    }

    @DeleteMapping("/encounters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping("/branches/{bid}/encounters")
    public List<Encounter> getByBranch(@PathVariable("bid") int bid) {
        return service.getByBranch(bid);
    }

    @GetMapping("/persons/{pid}/encounters")
    public List<Encounter> getByPerson(@PathVariable("pid") int pid) {
        return service.getByPerson(pid);
    }
}
