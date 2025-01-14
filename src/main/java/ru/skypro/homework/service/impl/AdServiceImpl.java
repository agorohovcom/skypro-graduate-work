package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.create_update_dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.AppMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdService;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdsRepository adsRepository;
    private final AppMapper appMapper;


    @Override
    public Ads getAllAds() {
        return new Ads();
    }

    @Override
    public Ad addAd(CreateOrUpdateAd createOrUpdateAd) {
        AdEntity adEntity = appMapper.createOrUpdateToAdEntity(createOrUpdateAd);
        Ad ad = appMapper.adEntitytoAd(adEntity);
        adsRepository.save(adEntity);
        return ad;
    }

    @Override
    public ExtendedAd getAd(Integer id) {
        AdEntity adEntity = adsRepository.findById(id).orElseThrow(RuntimeException::new);
        return appMapper.adEntitytoExtendedAd(adEntity);
    }

    @Override
    public void deleteAd(Integer id) {
        adsRepository.deleteById(id);
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
