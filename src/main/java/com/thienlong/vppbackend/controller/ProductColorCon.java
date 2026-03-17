package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.NameColor;
import com.thienlong.vppbackend.model.ProductColor;
import com.thienlong.vppbackend.service.NameColorSer;
import com.thienlong.vppbackend.service.ProductColorSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product_colors")
public class ProductColorCon {
    private final ProductColorSer ser;

    public ProductColorCon(ProductColorSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<ProductColor> getAllProductColors() {
        return ser.getAllProductColors();
    }
}
