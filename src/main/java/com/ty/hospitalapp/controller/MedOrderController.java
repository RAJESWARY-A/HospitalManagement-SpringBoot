package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.MedOrder;
import com.ty.hospitalapp.service.MedOrderService;
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
public class MedOrderController {

    private final MedOrderService service;

    public MedOrderController(MedOrderService service) {
        this.service = service;
    }

    @PostMapping("/encounters/{eid}/medorders")
    @ResponseStatus(HttpStatus.CREATED)
    public MedOrder save(@PathVariable("eid") int eid, @RequestBody MedOrder medOrder) {
        return service.save(eid, medOrder);
    }

    @GetMapping("/encounters/{eid}/medorders")
    public List<MedOrder> getByEncounter(@PathVariable("eid") int eid) {
        return service.getByEncounter(eid);
    }

    @GetMapping("/medorders")
    public List<MedOrder> getAll() {
        return service.getAll();
    }

    @GetMapping("/medorders/{id}")
    public MedOrder get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/medorders/{id}")
    public MedOrder update(@PathVariable("id") int id, @RequestBody MedOrder medOrder) {
        return service.update(id, medOrder);
    }

    @DeleteMapping("/medorders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping("/medorders/doctor/{name}")
    public List<MedOrder> getByDoctor(@PathVariable("name") String name) {
        return service.getByDoctor(name);
    }

    @GetMapping("/medorders/date/{date}")
    public List<MedOrder> getByDate(@PathVariable("date") String date) {
        return service.getByDate(date);
    }
}
