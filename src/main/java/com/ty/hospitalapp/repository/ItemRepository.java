package com.ty.hospitalapp.repository;

import java.util.List;
import com.ty.hospitalapp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByMedOrder_Mid(int mid);
}
