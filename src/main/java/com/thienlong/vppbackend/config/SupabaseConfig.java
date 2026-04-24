package com.thienlong.vppbackend.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

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
                .baseUrl(supabaseUrl + "/rest/v1")
                .defaultHeader("apikey", supabaseKey)
//                .defaultHeader("Authorization", "Bearer " + supabaseKey)
                .build();
    }

    @Bean
    @Qualifier("userVerifiedClient")
    public WebClient userVerifiedClient() {
        return WebClient.builder()
                .baseUrl(supabaseUrl + "/auth/v1")
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
}
