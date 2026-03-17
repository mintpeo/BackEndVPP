package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.Product;
import com.thienlong.vppbackend.repository.ProductRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSer {
    private final ProductRes res;

    public ProductSer(ProductRes res) {
        this.res = res;
    }

    public List<Product> getAllProducts() {
        return res.findAll();
    }
}
