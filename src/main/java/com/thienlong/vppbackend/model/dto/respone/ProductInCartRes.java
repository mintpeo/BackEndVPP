package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

@Data
public class ProductInCartRes {
    private Long id;
    private String sku;
    private String name;
    private String brand;
    private String category;
    private Integer price;
    private Integer originalPrice;
    private String currency;
    private Integer stock;
    private Double rating;
    private Integer review;
    private String description;
}
