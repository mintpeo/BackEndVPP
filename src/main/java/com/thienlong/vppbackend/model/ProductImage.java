package com.thienlong.vppbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("product_id")
    private String name;

    @JsonProperty("image")
    private String image;
}
