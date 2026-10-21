package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.Branch;
import com.ty.hospitalapp.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {

    private final BranchService service;

    public BranchController(BranchService service) {
        this.service = service;
    }

    @PostMapping("/hospitals/{hid}/branches")
    @ResponseStatus(HttpStatus.CREATED)
    public Branch save(@PathVariable("hid") int hid, @Valid @RequestBody Branch branch) {
        return service.save(hid, branch);
    }

    @GetMapping("/hospitals/{hid}/branches")
    public List<Branch> getByHospital(@PathVariable("hid") int hid) {
        return service.getByHospital(hid);
    }

    @GetMapping("/branches")
    public List<Branch> getAll() {
        return service.getAll();
    }

    @GetMapping("/branches/{id}")
    public Branch get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/branches/{id}")
    public Branch update(@PathVariable("id") int id, @Valid @RequestBody Branch branch) {
        return service.update(id, branch);
    }

    @DeleteMapping("/branches/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
