package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.NameColor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class NameColorRes {
    private final WebClient client;

    public NameColorRes(WebClient client) {
        this.client = client;
    }

    public List<NameColor> findAll() {
        return client.get().uri("/name_colors").retrieve().bodyToFlux(NameColor.class).collectList().block();
    }
}