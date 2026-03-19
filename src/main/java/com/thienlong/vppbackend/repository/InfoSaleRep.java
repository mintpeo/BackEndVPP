package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.entity.InfoSale;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class InfoSaleRep {
    private final WebClient client;

    public InfoSaleRep(WebClient client) {
        this.client = client;
    }

    public List<InfoSale> findAll() {
        return client.get().uri("/infoSales").retrieve().bodyToFlux(InfoSale.class).collectList().block();
    }
}