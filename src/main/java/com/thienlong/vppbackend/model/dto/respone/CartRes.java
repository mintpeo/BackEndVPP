package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

@Data
public class CartRes {
    private long id;
    private int quantity;
    private long productId;
    private String image;
    private String type;
    private String name;
    private int price;
    private int originalPrice;
    private String currency;
}
