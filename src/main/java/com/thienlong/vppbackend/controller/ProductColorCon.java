package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.ProductColor;
import com.thienlong.vppbackend.service.ProductColorSer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product_color")
@CrossOrigin(origins = "${app.frontend.url}")
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
