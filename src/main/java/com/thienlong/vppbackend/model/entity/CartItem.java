package com.thienlong.vppbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @JsonProperty("id")
    private long id;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("image")
    private String image;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private int price;

    @JsonProperty("originalPrice")
    private int originalPrice;

    @JsonProperty("currency")
    private String currency;
}
