package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.request.*;
import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.dto.respone.UserVerifyRes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.UUID;

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
    public UserRes saveNewUser(SignUserReq req, UUID id, String accToken) {
        Map<String, Object> body = Map.of(
                "id", id,
                "email", req.getEmail(),
                "password", req.getPassword(),
                "firstName", req.getFirstName(),
                "lastName", req.getLastName(),
                "phone", req.getPhone(),
                "role", req.getRole()
        );

        return guestsClient.post().uri("/users")
                .header("Authorization", "Bearer " + accToken)
                .header("Content-Type", "application/json")
                .header("Prefer", "return=representation")
                .bodyValue(body).retrieve().bodyToFlux(UserRes.class).blockFirst();
    }

    // Login User
    public SupabaseAuthRes checkLoginUser(LoginUserReq user) {
        return userVerifiedClient.post().uri("/token?grant_type=password")
                .bodyValue(user).retrieve().bodyToFlux(SupabaseAuthRes.class).blockFirst();
    }

    // Get Info User
    public UserRes getInfoUser(String accToken) {
        return guestsClient.get().uri("/users")
                .header("Authorization", "Bearer " + accToken)
                .retrieve().bodyToFlux(UserRes.class).blockFirst();
    }

    // Check Email
    public boolean existsEmail(String email) {
        EmailCheckReq res = adminClient.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + email)
                .queryParam("select", "email").build())
                .retrieve().bodyToFlux(EmailCheckReq.class).blockFirst();

        return res != null;
    }

    // Save Info User Path
    public InfoUserReq saveInfoUserPath(InfoUserReq req) {
        return guestsClient.patch().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("id", "eq." + req.getId()).build())
                .header("Authorization", "Bearer " + req.getAccToken())
                .bodyValue(req).retrieve().bodyToMono(InfoUserReq.class).block();
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