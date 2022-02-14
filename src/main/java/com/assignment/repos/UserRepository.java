package com.assignment.repos;

import org.springframework.data.repository.CrudRepository;

import com.assignment.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}