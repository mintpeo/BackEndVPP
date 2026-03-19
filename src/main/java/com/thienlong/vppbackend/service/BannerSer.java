package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.entity.Banner;
import com.thienlong.vppbackend.repository.BannerRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerSer {
    private final BannerRep rep;

    public BannerSer(BannerRep rep) {
        this.rep = rep;
    }

    public List<Banner> getAllBanners() {
        return rep.findAll();
    }
}
