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

    @JsonProperty("cart_id")
    private long cartId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("type")
    private String type;

    @JsonProperty("product_id")
    private long productId;
}
