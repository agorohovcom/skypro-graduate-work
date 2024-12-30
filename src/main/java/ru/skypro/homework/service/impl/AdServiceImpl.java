package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public Ads getAllAds() {
        return new Ads();
    }

    @Override
    public Integer addAd(CreateOrUpdateAd ad, MultipartFile image) {
        return 1;
    }

    @Override
    public ExtendedAd getAd(Integer id) {
        return new ExtendedAd();
    }

    @Override
    public void deleteAd(Integer id) {

    }

    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        return new Ad();
    }

    @Override
    public Ads getAdsMe() {
        return new Ads();
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile image) {

    }
}
