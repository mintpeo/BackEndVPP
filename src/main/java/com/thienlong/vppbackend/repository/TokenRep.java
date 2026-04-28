package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Repository
public class TokenRep {
    private final WebClient userVerifiedClient;

    public TokenRep(@Qualifier("userVerifiedClient") WebClient userVerifiedClient) {
        this.userVerifiedClient = userVerifiedClient;
    }

    // Reset AT By RT
    public SupabaseAuthRes resetATByRT(String refreshToken) {
        Map<String, String> body = Map.of(
                "refresh_token", refreshToken
        );

        return userVerifiedClient.post().uri("/token?grant_type=refresh_token")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(SupabaseAuthRes.class).block(); // Mono tra ve 1 Object, Flux tra ve 1 list
    }
}
