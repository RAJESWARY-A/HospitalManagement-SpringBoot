package com.ty.hospitalapp.controller;

import java.util.List;
import com.ty.hospitalapp.entity.Item;
import com.ty.hospitalapp.service.ItemService;
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
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping("/medorders/{mid}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Item save(@PathVariable("mid") int mid, @RequestBody Item item) {
        return service.save(mid, item);
    }

    @GetMapping("/medorders/{mid}/items")
    public List<Item> getByMedOrder(@PathVariable("mid") int mid) {
        return service.getByMedOrder(mid);
    }

    @GetMapping("/items")
    public List<Item> getAll() {
        return service.getAll();
    }

    @GetMapping("/items/{id}")
    public Item get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PutMapping("/items/{id}")
    public Item update(@PathVariable("id") int id, @RequestBody Item item) {
        return service.update(id, item);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
