package com.thienlong.vppbackend.model.dto.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthInfoRes {
    @JsonProperty("id")
    private UUID id;
}
