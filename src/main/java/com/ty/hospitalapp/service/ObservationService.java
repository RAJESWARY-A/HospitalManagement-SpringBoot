package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.Observation;
import com.ty.hospitalapp.repository.ObservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ObservationService {

    private final ObservationRepository repo;
    private final EncounterService encounterService;

    public ObservationService(ObservationRepository repo, EncounterService encounterService) {
        this.repo = repo;
        this.encounterService = encounterService;
    }

    public Observation save(int eid, Observation data) {
        data.setEncounter(encounterService.get(eid));
        return repo.save(data);
    }

    public List<Observation> getAll() {
        return repo.findAll();
    }

    public Observation get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Observation " + id + " not found"));
    }

    public Observation update(int id, Observation data) {
        Observation old = get(id);
        old.setDoctorName(data.getDoctorName());
        old.setRemarks(data.getRemarks());
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }

    public List<Observation> getByEncounter(int eid) {
        encounterService.get(eid);
        return repo.findByEncounter_Eid(eid);
    }

    public List<Observation> getByDoctor(String doctorName) {
        return repo.findByDoctorName(doctorName);
    }
}
