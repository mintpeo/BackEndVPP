package com.thienlong.vppbackend.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartReq {
    @JsonProperty("user_id")
    private Integer user_id;

    @JsonProperty("quantity")
    private int quantity;

    @JsonIgnore
    private ProductInCartReq product;

    @JsonProperty("productCart_id")
    private Integer productCartId;
}
