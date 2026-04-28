package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import com.thienlong.vppbackend.repository.TokenRep;
import org.springframework.stereotype.Service;

@Service
public class TokenSer {
    private final TokenRep rep;

    public TokenSer(TokenRep rep) {
        this.rep = rep;
    }

    // Reset AT By RT
    public SupabaseAuthRes resetATByRT(String refreshToken) {
        return rep.resetATByRT(refreshToken);
    }
}
