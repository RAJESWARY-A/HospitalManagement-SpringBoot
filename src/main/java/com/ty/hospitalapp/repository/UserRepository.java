package com.ty.hospitalapp.repository;

import java.util.List;
import com.ty.hospitalapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    List<User> findByRole(String role);
}
