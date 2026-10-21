package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.Address;
import com.ty.hospitalapp.entity.Branch;
import com.ty.hospitalapp.repository.BranchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BranchService {

    private final BranchRepository repo;
    private final HospitalService hospitalService;

    public BranchService(BranchRepository repo, HospitalService hospitalService) {
        this.repo = repo;
        this.hospitalService = hospitalService;
    }

    public Branch save(int hid, Branch data) {
        data.setHospital(hospitalService.get(hid));
        return repo.save(data);
    }

    public List<Branch> getAll() {
        return repo.findAll();
    }

    public Branch get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch " + id + " not found"));
    }

    public Branch update(int id, Branch data) {
        Branch old = get(id);
        old.setName(data.getName());
        old.setPhone(data.getPhone());
        old.setEmail(data.getEmail());
        Address a = data.getAddress();
        if (a != null) {
            if (old.getAddress() == null) {
                old.setAddress(a);
            } else {
                Address current = old.getAddress();
                current.setStreet(a.getStreet());
                current.setState(a.getState());
                current.setCountry(a.getCountry());
                current.setPin(a.getPin());
            }
        }
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }

    public List<Branch> getByHospital(int hid) {
        hospitalService.get(hid);
        return repo.findByHospital_Hid(hid);
    }
}
