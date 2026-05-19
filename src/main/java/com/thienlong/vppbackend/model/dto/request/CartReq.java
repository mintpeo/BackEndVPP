package com.thienlong.vppbackend.model.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CartReq {
    private Long cartId;
    private UUID userId;
    private long productId;
    private String image;
    private int quantity;
    private String type;
    private String name;
    private int price;
    private int originalPrice;
    private String currency;
}