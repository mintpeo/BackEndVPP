package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class UserRes {
    private final WebClient client;

    public UserRes(WebClient client) {
        this.client = client;
    }

    public List<User> findAll() {
        return client.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
    }
}