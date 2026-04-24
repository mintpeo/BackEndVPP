package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.entity.VerificationCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Repository
public class VerificationRep {
    private final WebClient adminClient;

    public VerificationRep(@Qualifier("adminClient") WebClient adminClient) {
        this.adminClient = adminClient;
    }

    public boolean saveVerificationCodeToData(VerificationCode vc) {
        return adminClient.post().uri("/verification_code").bodyValue(vc).retrieve().toBodilessEntity().
                map(res -> res.getStatusCode().is2xxSuccessful()).
                onErrorReturn(false).block();
    }

    public VerificationCode findEmailAndCode(String email, String code) {
        return adminClient.get().uri(uriBuilder -> uriBuilder.path("/verification_code").
                queryParam("email", "eq." + email).
                queryParam("code", "eq." + code).
                queryParam("order", "id.desc").build()).
                retrieve().bodyToFlux(VerificationCode.class).blockFirst();
    }

    public boolean updateVerificationCode(VerificationCode vc) {
        try {
            adminClient.patch().uri(uriBuilder -> uriBuilder.path("/verification_code").
                            queryParam("email", "eq." + vc.getEmail()).
                            queryParam("code", "eq." + vc.getCode()).build()).
                    bodyValue(Map.of("verified", true)).
                    retrieve().toBodilessEntity().block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
