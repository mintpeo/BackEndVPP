package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.InfoSale;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class InfoSaleRes {
    private final WebClient client;

    public InfoSaleRes(WebClient client) {
        this.client = client;
    }

    public List<InfoSale> findAll() {
        return client.get().uri("/infoSales").retrieve().bodyToFlux(InfoSale.class).collectList().block();
    }
}