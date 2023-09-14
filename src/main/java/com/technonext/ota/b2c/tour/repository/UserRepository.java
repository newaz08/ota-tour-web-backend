package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUsername(String username);
}
