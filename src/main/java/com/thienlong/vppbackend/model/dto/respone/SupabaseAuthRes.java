package com.thienlong.vppbackend.model.dto.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SupabaseAuthRes {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("user")
    private AuthInfoRes user;
}
