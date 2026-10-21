package com.ty.hospitalapp.repository;

import java.util.List;
import com.ty.hospitalapp.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

    List<Branch> findByHospital_Hid(int hid);
}
