package com.reto.demo.repository;

import com.reto.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.dni = ?1")
    Optional<User> getUserByDni(String dni);
}
