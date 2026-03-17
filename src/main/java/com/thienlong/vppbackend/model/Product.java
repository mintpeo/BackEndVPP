package com.thienlong.vppbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("category")
    private String category;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("originalPrice")
    private Integer originalPrice;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("review")
    private Integer review;

    @JsonProperty("description")
    private String description;

    private List<ProductColor> colors;

    private List<ProductImage> images;
}
