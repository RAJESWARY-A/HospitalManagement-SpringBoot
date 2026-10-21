package com.ty.hospitalapp.repository;

import java.util.List;
import com.ty.hospitalapp.entity.MedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedOrderRepository extends JpaRepository<MedOrder, Integer> {

    List<MedOrder> findByEncounter_Eid(int eid);

    List<MedOrder> findByDoctorName(String doctorName);

    List<MedOrder> findByDate(String date);
}
