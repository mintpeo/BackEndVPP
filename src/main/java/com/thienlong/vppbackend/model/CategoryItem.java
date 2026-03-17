package com.thienlong.vppbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryItem {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("category_id")
    private Integer cateId;

    @JsonProperty("item")
    private String item;
}
