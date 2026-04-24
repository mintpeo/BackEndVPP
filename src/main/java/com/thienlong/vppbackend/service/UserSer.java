package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.request.LoginUserReq;
import com.thienlong.vppbackend.model.dto.request.PassChangeReq;
import com.thienlong.vppbackend.model.dto.request.SignUserWithIdReq;
import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.dto.respone.UserVerifyRes;
import com.thienlong.vppbackend.model.entity.User;
import com.thienlong.vppbackend.model.dto.request.SignUserReq;
import com.thienlong.vppbackend.repository.UserRep;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public SignUserWithIdReq signUser(SignUserReq req) {
        SupabaseAuthRes auth = createATForUserVerified(req);
        if (auth == null) return null;

        SignUserWithIdReq newUser = new SignUserWithIdReq();
        newUser.setId(auth.getUser().getId());
        newUser.setLastName(req.getLastName());
        newUser.setFirstName(req.getFirstName());
        newUser.setEmail(req.getEmail());
        newUser.setPhone(req.getPhone());
        newUser.setPassword(req.getPassword());
        newUser.setRole(1);

        return rep.saveNewUser(newUser, auth.getAccess_token());
    }

    // Login User
    public UserRes loginUser(LoginUserReq user) {
        return rep.checkLoginUser(user);
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
