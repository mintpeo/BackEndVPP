package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.entity.InfoSale;
import com.thienlong.vppbackend.repository.InfoSaleRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoSaleSer {
    private final InfoSaleRep rep;

    public InfoSaleSer(InfoSaleRep rep) {
        this.rep = rep;
    }

    public List<InfoSale> getAllInfoSales() {
        return rep.findAll();
    }
}
