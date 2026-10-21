package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.Encounter;
import com.ty.hospitalapp.repository.EncounterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EncounterService {

    private final EncounterRepository repo;
    private final BranchService branchService;
    private final PersonService personService;

    public EncounterService(EncounterRepository repo, BranchService branchService, PersonService personService) {
        this.repo = repo;
        this.branchService = branchService;
        this.personService = personService;
    }

    public Encounter save(int branchId, int personId, Encounter data) {
        data.setBranch(branchService.get(branchId));
        data.setPerson(personService.get(personId));
        return repo.save(data);
    }

    public List<Encounter> getAll() {
        return repo.findAll();
    }

    public Encounter get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Encounter " + id + " not found"));
    }

    public Encounter update(int id, Encounter data) {
        Encounter old = get(id);
        old.setReason(data.getReason());
        old.setDate(data.getDate());
        old.setDischarge(data.getDischarge());
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }

    public List<Encounter> getByBranch(int bid) {
        branchService.get(bid);
        return repo.findByBranch_Bid(bid);
    }

    public List<Encounter> getByPerson(int pid) {
        personService.get(pid);
        return repo.findByPerson_Pid(pid);
    }
}
