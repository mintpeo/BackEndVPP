package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.Product;
import com.thienlong.vppbackend.service.ProductSer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "${app.frontend.url}")
public class ProductCon {
    private final ProductSer ser;

    public ProductCon(ProductSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return ser.getAllProducts();
    }
}
