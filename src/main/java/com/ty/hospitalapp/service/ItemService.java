package com.ty.hospitalapp.service;

import java.util.List;
import com.ty.hospitalapp.entity.Item;
import com.ty.hospitalapp.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemService {

    private final ItemRepository repo;
    private final MedOrderService medOrderService;

    public ItemService(ItemRepository repo, MedOrderService medOrderService) {
        this.repo = repo;
        this.medOrderService = medOrderService;
    }

    public Item save(int mid, Item data) {
        data.setMedOrder(medOrderService.get(mid));
        return repo.save(data);
    }

    public List<Item> getAll() {
        return repo.findAll();
    }

    public Item get(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Item " + id + " not found"));
    }

    public Item update(int id, Item data) {
        Item old = get(id);
        old.setName(data.getName());
        old.setCost(data.getCost());
        old.setQuantity(data.getQuantity());
        return repo.save(old);
    }

    public void delete(int id) {
        repo.delete(get(id));
    }

    public List<Item> getByMedOrder(int mid) {
        medOrderService.get(mid);
        return repo.findByMedOrder_Mid(mid);
    }
}
