package com.example.todos.repositories;

import com.example.todos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByEmail(String email);

    User findByUsername(String username);
}
