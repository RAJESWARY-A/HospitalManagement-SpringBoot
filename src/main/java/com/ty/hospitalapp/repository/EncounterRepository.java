package com.ty.hospitalapp.repository;

import java.util.List;
import com.ty.hospitalapp.entity.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {

    List<Encounter> findByBranch_Bid(int bid);

    List<Encounter> findByPerson_Pid(int pid);
}
