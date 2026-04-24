package com.thienlong.vppbackend.model.dto.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SupabaseAuthRes {
    @JsonProperty("access_token")
    private String access_token;

    @JsonProperty("token_type")
    private String token_type;

    @JsonProperty("expires_in")
    private Long expires_in;

    @JsonProperty("refresh_token")
    private String refresh_token;

    @JsonProperty("user")
    private AuthInfoRes user;
}
