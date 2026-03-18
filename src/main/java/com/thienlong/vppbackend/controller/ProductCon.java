package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.Product;
import com.thienlong.vppbackend.service.ProductSer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
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

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        List<Product> products = ser.getAllProducts();
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }
}
