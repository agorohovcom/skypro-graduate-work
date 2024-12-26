package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "API для работы с объявлениями")
public class AdController {

    // todo сервис ещё не реализован
    private final AdService adService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public AdsDto getAllAds() {
//        return adService.getAllAds();
        return new AdsDto();
    }

    @PostMapping
    @Operation(summary = "Добавление объявления")
    public Long addAd(@RequestPart("properties") CreateOrUpdateAdDto ad, @RequestPart("image") MultipartFile image) {
//        return adService.addAd(ad, image);
        return 1L;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ExtendedAdDto getAd(@PathVariable Long id) {
//        return adService.getAd(id);
        return new ExtendedAdDto();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public void deleteAd(@PathVariable Long id) {
//        adService.deleteAd(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public Ad updateAd(@PathVariable Long id, @RequestBody CreateOrUpdateAdDto ad) {
//        return adService.updateAd(id, ad);
        return new Ad();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public AdsDto getAdsMe() {
//        return adService.getAdsMe();
        return new AdsDto();
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public void updateAdImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
//        adService.updateAdImage(id, image);
    }
}