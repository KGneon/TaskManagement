package com.team.repository;

import com.team.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNameContainingOrSurnameContainingOrEmailContaining(String name, String surname, String email);

}
