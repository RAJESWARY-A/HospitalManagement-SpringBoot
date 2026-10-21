package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.Hospital;
import com.ty.hospitalapp.repository.HospitalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HospitalService {

    private final HospitalRepository repo;

    public HospitalService(HospitalRepository repo) {
        this.repo = repo;
    }

    public Hospital save(Hospital data) {
        return repo.save(data);
    }

    public List<Hospital> getAll() {
        return repo.findAll();
    }

    public Hospital get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital " + id + " not found"));
    }

    public Hospital update(int id, Hospital data) {
        Hospital old = get(id);
        old.setName(data.getName());
        old.setWebsite(data.getWebsite());
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }
}
