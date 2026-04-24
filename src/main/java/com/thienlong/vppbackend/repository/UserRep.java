package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.request.*;
import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.dto.respone.UserVerifyRes;
import com.thienlong.vppbackend.model.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Repository
public class UserRep {
    private final WebClient guestsClient;
    private final WebClient adminClient;
    private final WebClient userVerifiedClient;

    public UserRep(@Qualifier("guestsClient") WebClient guestsClient,
                   @Qualifier("adminClient") WebClient adminClient,
                   @Qualifier("userVerifiedClient") WebClient userVerifiedClient) {
        this.guestsClient = guestsClient;
        this.adminClient = adminClient;
        this.userVerifiedClient = userVerifiedClient;
    }

    // Check table verifyCode -> get verify
    public UserVerifyRes getVerifyByEmail(String email) {
        return adminClient.get().uri(uriBuilder -> uriBuilder.path("/verification_code").
                        queryParam("email", "eq." + email).
                        queryParam("order", "id.desc").build()).
                retrieve().bodyToFlux(UserVerifyRes.class).blockFirst();
    }

    // Create Access Token
    public SupabaseAuthRes createAT(SignUserReq req) {
        Map<String, String> body = Map.of(
                "email", req.getEmail(),
                "password", req.getPassword()
        );

        return userVerifiedClient.post().uri("/signup").bodyValue(body).retrieve().bodyToMono(SupabaseAuthRes.class).block();
    }

    // Sign User
    public SignUserWithIdReq saveNewUser(SignUserWithIdReq req, String accToken) {
        return guestsClient.post().uri("/users").
                header("Authorization", "Bearer " + accToken).
                bodyValue(req).retrieve().bodyToMono(SignUserWithIdReq.class).block();
    }

    // Login User
    public UserRes checkLoginUser(LoginUserReq user) {
        return guestsClient.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + user.getEmail())
                .queryParam("password", "eq." + user.getPass())
                .build()).retrieve().bodyToFlux(UserRes.class).blockFirst();
    }

    // Check Email
    public boolean existsEmail(String email) {
        EmailCheckReq res = adminClient.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + email)
                .queryParam("select", "email").build())
                .retrieve().bodyToFlux(EmailCheckReq.class).blockFirst();

        return res != null;
    }

    // Change Pass
    public boolean checkEmailAndPass(String email, String oldPass) {
        PassChangeReq res = guestsClient.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + email)
                .queryParam("password", "eq." + oldPass)
                .queryParam("select", "email").build())
                .retrieve().bodyToFlux(PassChangeReq.class).blockFirst();

        return res != null;
    }

    public boolean changePass(PassChangeReq user) {
        boolean checkUser = checkEmailAndPass(user.getEmail(), user.getOldPass());

        if (checkUser) {
            guestsClient.patch().uri(uriBuilder -> uriBuilder.path("/users")
                    .queryParam("email", "eq." + user.getEmail()).build())
                    .bodyValue(Map.of("password", user.getNewPass())).retrieve().toBodilessEntity().block();

            return true;
        }

        return false;
    }
}