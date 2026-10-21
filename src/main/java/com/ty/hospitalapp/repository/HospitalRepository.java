package com.ty.hospitalapp.repository;

import com.ty.hospitalapp.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
