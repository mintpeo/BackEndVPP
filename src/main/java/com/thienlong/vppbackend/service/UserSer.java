package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.request.InfoUserReq;
import com.thienlong.vppbackend.model.dto.request.LoginUserReq;
import com.thienlong.vppbackend.model.dto.request.PassChangeReq;
import com.thienlong.vppbackend.model.dto.SignUserWithToken;
import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.dto.respone.UserVerifyRes;
import com.thienlong.vppbackend.model.dto.request.SignUserReq;
import com.thienlong.vppbackend.model.entity.User;
import com.thienlong.vppbackend.repository.UserRep;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserSer {
    private final UserRep rep;

    public UserSer(UserRep rep) {
        this.rep = rep;
    }

    // 1. Check Verify Email
    public boolean checkProfileVerify(String email) {
        UserVerifyRes res = rep.getVerifyByEmail(email);
        if (res.isVerified()) return true;
        return false;
    }

    // 2. Create AT If Verify = True
    public SupabaseAuthRes createATForUserVerified(SignUserReq req) {
        if (checkProfileVerify(req.getEmail())) {
            return rep.createAT(req);
        }
        return null;
    }

    // Sign User
    public SignUserWithToken signUser(SignUserReq req) {
        SupabaseAuthRes auth = createATForUserVerified(req);
        if (auth == null) return null;
        String accToken = auth.getAccessToken();
        UUID id = auth.getUser().getId();
        UserRes user = rep.saveNewUser(req, id, accToken);

        SignUserWithToken savedUser = new SignUserWithToken();
        // Info
        savedUser.setLastName(user.getLastName());
        // Token
        savedUser.setAccessToken(auth.getAccessToken());
        savedUser.setRefreshToken(auth.getRefreshToken());
        savedUser.setExpiresIn(auth.getExpiresIn());


        return savedUser;
    }

    // Login User
    public SignUserWithToken loginUser(LoginUserReq req) {
        SupabaseAuthRes auth = rep.checkLoginUser(req);
        UserRes user = rep.getInfoUserByAT(auth.getAccessToken());

        SignUserWithToken savedUser = new SignUserWithToken();
        // Info
        savedUser.setLastName(user.getLastName());
        // Token
        savedUser.setAccessToken(auth.getAccessToken());
        savedUser.setRefreshToken(auth.getRefreshToken());
        savedUser.setExpiresIn(auth.getExpiresIn());

        return savedUser;
    }

    // Get Info User By Access Token
    public UserRes getInfoUserByAT(String AT) {
        return rep.getInfoUserByAT(AT);
    }

    public boolean changeInfoUser(InfoUserReq req) {
        InfoUserReq user = rep.saveInfoUserPath(req);
        return user != null;
    }

    // Check Email User
    public boolean emailCheck(String email) {
        return rep.existsEmail(email);
    }

    // Change Password User
    public boolean changePass(PassChangeReq user) {
        return rep.changePass(user);
    }
}
