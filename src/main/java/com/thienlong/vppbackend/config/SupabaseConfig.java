package com.thienlong.vppbackend.config;

import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class SupabaseConfig {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.secret.key}")
    private String secretKey;

    @Bean
    @Qualifier("guestsClient")
    public WebClient guestsClient() {
        // Cấu hình WebClient dùng chung với URL gốc và Header bảo mật
        return WebClient.builder()
                .baseUrl(supabaseUrl + "/rest/v1") // rest
                .defaultHeader("apikey", supabaseKey)
//                .defaultHeader("Authorization", "Bearer " + supabaseKey)
                .build();
    }

    @Bean
    @Qualifier("userVerifiedClient")
    public WebClient userVerifiedClient() {
        return WebClient.builder()
                .baseUrl(supabaseUrl + "/auth/v1") // auth
                .defaultHeader("apikey", supabaseKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Bean
    @Qualifier("adminClient")
    public WebClient adminClient() {
        return WebClient.builder()
                .baseUrl(supabaseUrl + "/rest/v1")
                .defaultHeader("apikey", secretKey)
                .build();
    }

    // HAM GHI LOI
//    return userVerifiedClient.post().uri("/token?grant_type=refresh_token")
//                .bodyValue(body)
//                .onStatus(HttpStatusCode::isError, response ->
//                      response.bodyToMono(String.class).flatMap(errorBody -> {
//                      //  In ra lỗi cụ thể như: "Invalid refresh token" hoặc "Token expired"
//                      System.err.println("Supabase Refresh Error: " + errorBody);
//                      return Mono.error(new RuntimeException(errorBody));
//                      }))
//                  .retrieve()
//                  .bodyToMono(SupabaseAuthRes .class).block(); // Mono tra ve 1 Object, Flux tra ve 1 list

}
