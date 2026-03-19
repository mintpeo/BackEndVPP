package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.request.LoginUserReq;
import com.thienlong.vppbackend.model.dto.request.PassChangeReq;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
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

    public List<User> getAllUsers() {
        return rep.findAll();
    }

    // Login User
    public UserRes loginUser(LoginUserReq user) {
        return rep.checkLoginUser(user);
    }

    // Sign User
    public SignUserReq signUser(SignUserReq dto) {
        return rep.saveNewUser(dto);
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
