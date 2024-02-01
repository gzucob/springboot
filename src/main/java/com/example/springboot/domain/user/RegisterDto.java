package com.example.springboot.domain.user;

public record RegisterDto (String login, String password, UserRole role) {
}
