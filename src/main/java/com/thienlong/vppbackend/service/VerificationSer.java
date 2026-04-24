package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.entity.VerificationCode;
import com.thienlong.vppbackend.repository.VerificationRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class VerificationSer {
    @Autowired
    private JavaMailSender mailSender;

    private final VerificationRep rep;

    public VerificationSer(VerificationRep rep) {
        this.rep = rep;
    }

    // Create code, time,...
    public VerificationCode createVerificationCode(String email) {
        String code = String.valueOf((int)(Math.random() * 900000) + 100000);

        VerificationCode entity = new VerificationCode();
        entity.setEmail(email);
        entity.setCode(code);
        entity.setExpiryTime(OffsetDateTime.now(ZoneOffset.UTC).plusMinutes(5)); // ton tai
        entity.setVerified(false);

        // Set up send mail
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Your Verification Code");
//        message.setText("Your code is: " + code);
//        mailSender.send(message);

        return entity;
    }

    // Send Code
    public boolean sendVerificationCode(String email) {
        VerificationCode vc = createVerificationCode(email);
        return rep.saveVerificationCodeToData(vc);
    }


    // Verify Code
    public boolean verifyCode(String email, String code) {
        VerificationCode vc = rep.findEmailAndCode(email, code);
        if (vc == null) return false;
        if (vc.isVerified()) return false;
        if (vc.getExpiryTime().isBefore(OffsetDateTime.now(ZoneOffset.UTC))) return false; // het han ma
        return rep.updateVerificationCode(vc);
    }
}
