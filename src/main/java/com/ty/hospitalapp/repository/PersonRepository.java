package com.ty.hospitalapp.repository;

import java.util.List;
import com.ty.hospitalapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByGender(String gender);

    List<Person> findByAge(int age);

    List<Person> findByPhone(long phone);
}
