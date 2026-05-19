package com.thienlong.vppbackend.model.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowProductRes {
    private long id;
    private String sku;
    private String name;
    private String brand;
    private String category;
    private int price;
    private int originalPrice;
    private String currency;
    private int stock;
    private double rating;
    private int review;
    private String description;
    private List<ShowProductColorRes> colors;
    private List<String> images;
}
