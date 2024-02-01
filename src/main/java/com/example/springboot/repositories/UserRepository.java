package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
