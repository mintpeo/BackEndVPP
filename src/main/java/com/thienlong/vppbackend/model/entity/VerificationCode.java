package com.thienlong.vppbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode { // dung de luu code, time,... roi dua vao database
    @JsonProperty("email")
    private String email;

    @JsonProperty("code")
    private String code;

    @JsonProperty("expiryTime")
    private OffsetDateTime expiryTime;

    @JsonProperty("verified")
    private boolean verified;
}
