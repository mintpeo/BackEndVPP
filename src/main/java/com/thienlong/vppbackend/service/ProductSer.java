package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.entity.Product;
import com.thienlong.vppbackend.repository.ProductRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSer {
    private final ProductRep rep;

    public ProductSer(ProductRep rep) {
        this.rep = rep;
    }

    public List<Product> getAllProducts() {
        return rep.findAll();
    }

    // Find Product -> Detail
    public Product getProductById(Long id) {
        List<Product> products = rep.findAll();
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }
}
