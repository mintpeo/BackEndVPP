package com.thienlong.vppbackend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class CartRep {
    private final WebClient client;

    public CartRep(WebClient client) {
        this.client = client;
    }

    // Get List Cart By Email User
//    public
}