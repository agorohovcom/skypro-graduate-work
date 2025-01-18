package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.create_update_dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.AppMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdsRepository adsRepository;
    private final AppMapper appMapper;

    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntities = adsRepository.findAll();
        return getAdsFromListOfAdEntity(adEntities);
    }

    @Override
    public Ad addAd(CreateOrUpdateAd createOrUpdateAd) {
        AdEntity adEntity = appMapper.createOrUpdateToAdEntity(createOrUpdateAd, getCurrentUserEntity());
        adsRepository.save(adEntity);
        return appMapper.adEntitytoAd(adEntity);
    }

    @Override
    public ExtendedAd getAd(Integer id) {
        AdEntity adEntity = adsRepository.findById(id).orElseThrow(() ->
                new AdNotFoundException("Не найдено объявление с id: " + id));
        return appMapper.adEntitytoExtendedAd(adEntity);
    }

    @Override
    public void deleteAd(Integer id) {
        AdEntity adEntity = adsRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Не найдено объявление с id: " + id));

        if (!adEntity.getAuthor().getId().equals(getCurrentUserEntity().getId())) {
            throw new ForbiddenException("У вас нет прав для удаления объявления с id: " + id);
        }

        adsRepository.deleteById(id);
    }

    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        AdEntity adEntity = adsRepository.findById(id).orElseThrow(() ->
                new AdNotFoundException("Не найдено объявление с id: " + id));

        if (!adEntity.getAuthor().getId().equals(getCurrentUserEntity().getId())) {
            throw new ForbiddenException("У вас нет прав для изменения объявления с id: " + id);
        }

        appMapper.updateAdEntityFromDto(createOrUpdateAd, adEntity);
        adsRepository.save(adEntity);
        return appMapper.adEntitytoAd(adEntity);
    }

    @Override
    public Ads getAdsMe() {
        List<AdEntity> adEntities = adsRepository.findByAuthorId(getCurrentUserEntity().getId());
        return getAdsFromListOfAdEntity(adEntities);
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile image) {

    }

    // возвращает текущего юзера для проверки прав
    private UserEntity getCurrentUserEntity() {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return myUserPrincipal.getUser();
    }

    // принимает список AdEntity и возвращает объект Ads
    private Ads getAdsFromListOfAdEntity(List<AdEntity> adEntities) {
        List<Ad> adList = adEntities.stream()
                .map(appMapper::adEntitytoAd)
                .toList();
        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);
        return ads;
    }
}
