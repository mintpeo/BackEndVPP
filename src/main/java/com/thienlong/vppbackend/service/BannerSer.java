package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.Banner;
import com.thienlong.vppbackend.repository.BannerRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerSer {
    private final BannerRes res;

    public BannerSer(BannerRes res) {
        this.res = res;
    }

    public List<Banner> getAllBanners() {
        return res.findAll();
    }
}
