package com.ty.hospitalapp.repository;

import java.util.List;
import com.ty.hospitalapp.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Integer> {

    List<Observation> findByEncounter_Eid(int eid);

    List<Observation> findByDoctorName(String doctorName);
}
