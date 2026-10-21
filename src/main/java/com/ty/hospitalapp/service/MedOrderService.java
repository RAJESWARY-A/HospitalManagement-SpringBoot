package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.MedOrder;
import com.ty.hospitalapp.repository.MedOrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MedOrderService {

    private final MedOrderRepository repo;
    private final EncounterService encounterService;

    public MedOrderService(MedOrderRepository repo, EncounterService encounterService) {
        this.repo = repo;
        this.encounterService = encounterService;
    }

    public MedOrder save(int eid, MedOrder data) {
        data.setEncounter(encounterService.get(eid));
        return repo.save(data);
    }

    public List<MedOrder> getAll() {
        return repo.findAll();
    }

    public MedOrder get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "MedOrder " + id + " not found"));
    }

    public MedOrder update(int id, MedOrder data) {
        MedOrder old = get(id);
        old.setDoctorName(data.getDoctorName());
        old.setDate(data.getDate());
        old.setTotal(data.getTotal());
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }

    public List<MedOrder> getByEncounter(int eid) {
        encounterService.get(eid);
        return repo.findByEncounter_Eid(eid);
    }

    public List<MedOrder> getByDoctor(String doctorName) {
        return repo.findByDoctorName(doctorName);
    }

    public List<MedOrder> getByDate(String date) {
        return repo.findByDate(date);
    }
}
