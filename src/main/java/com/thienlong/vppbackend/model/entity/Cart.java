package com.thienlong.vppbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("product_id")
    private Long product_id;

    @JsonProperty("quantity")
    private Integer quantity;
}
