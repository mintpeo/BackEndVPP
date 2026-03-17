package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.Banner;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class BannerRes {
    private final WebClient client;

    public BannerRes(WebClient client) {
        this.client = client;
    }

    public List<Banner> findAll() {
        return client.get().uri("/banners").retrieve().bodyToFlux(Banner.class).collectList().block();
    }
}