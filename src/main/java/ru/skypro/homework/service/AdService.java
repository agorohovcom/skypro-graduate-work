package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdService {
    Ads getAllAds();

    Integer addAd(CreateOrUpdateAd ad, MultipartFile image);

    ExtendedAd getAd(Integer id);

    void deleteAd(Integer id);

    Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd);

    Ads getAdsMe();

    void updateAdImage(Integer id, MultipartFile image);
}
