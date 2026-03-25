package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.request.EmailCheckReq;
import com.thienlong.vppbackend.model.dto.request.LoginUserReq;
import com.thienlong.vppbackend.model.dto.request.PassChangeReq;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.entity.User;
import com.thienlong.vppbackend.model.dto.request.SignUserReq;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Repository
public class UserRep {
    private final WebClient client;

    public UserRep(WebClient client) {
        this.client = client;
    }

    public List<User> findAll() {
        return client.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
    }

    // Login User
    public UserRes checkLoginUser(LoginUserReq user) {
        return client.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + user.getEmail())
                .queryParam("password", "eq." + user.getPass())
                .build()).retrieve().bodyToFlux(UserRes.class).blockFirst();
    }

    // Sign User
    public SignUserReq saveNewUser(SignUserReq dto) {
        return client.post().uri("/users").bodyValue(dto).retrieve().bodyToMono(SignUserReq.class).block();
    }

    // Check Email
    public boolean existsEmail(String email) {
        EmailCheckReq res = client.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + email)
                .queryParam("select", "email").build())
                .retrieve().bodyToFlux(EmailCheckReq.class).blockFirst();

        return res != null;
    }

    // Change Pass
    public boolean checkEmailAndPass(String email, String oldPass) {
        PassChangeReq res = client.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + email)
                .queryParam("password", "eq." + oldPass)
                .queryParam("select", "email").build())
                .retrieve().bodyToFlux(PassChangeReq.class).blockFirst();

        return res != null;
    }

    public boolean changePass(PassChangeReq user) {
        boolean checkUser = checkEmailAndPass(user.getEmail(), user.getOldPass());

        if (checkUser) {
            client.patch().uri(uriBuilder -> uriBuilder.path("/users")
                    .queryParam("email", "eq." + user.getEmail()).build())
                    .bodyValue(Map.of("password", user.getNewPass())).retrieve().toBodilessEntity().block();

            return true;
        }

        return false;
    }
}