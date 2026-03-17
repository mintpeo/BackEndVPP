package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.InfoSale;
import com.thienlong.vppbackend.repository.InfoSaleRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoSaleSer {
    private final InfoSaleRes res;

    public InfoSaleSer(InfoSaleRes res) {
        this.res = res;
    }

    public List<InfoSale> getAllInfoSales() {
        return res.findAll();
    }
}
