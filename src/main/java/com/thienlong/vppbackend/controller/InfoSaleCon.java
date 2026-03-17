package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.InfoSale;
import com.thienlong.vppbackend.service.InfoSaleSer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/infoSales")
@CrossOrigin(origins = "${app.frontend.url}")
public class InfoSaleCon {
    private final InfoSaleSer ser;

    public InfoSaleCon(InfoSaleSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<InfoSale> getAllInfoSales() {
        return ser.getAllInfoSales();
    }
}
