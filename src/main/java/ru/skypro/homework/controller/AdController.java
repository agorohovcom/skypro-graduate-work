package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdController {

    // todo сервис ещё не реализован
    private final AdService adService;

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений",
            operationId = "getAllAds"
    )
    // todo 200
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    @PostMapping
    @Operation(
            summary = "Добавление объявления",
            operationId = "addAd"
    )
    // todo 201, 401
    public Integer addAd(
            @Valid @RequestPart("properties") CreateOrUpdateAd ad,
            @RequestPart("image") MultipartFile image
    ) {
        return adService.addAd(ad, image);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об объявлении",
            operationId = "getAds"
    )
    // todo 200, 401, 404
    public ExtendedAd getAds(@PathVariable Integer id) {
        return adService.getAd(id);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление объявления",
            operationId = "removeAd"
    )
    // todo 204, 401, 403, 404
    public void removeAd(@PathVariable Integer id) {
        adService.deleteAd(id);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновление информации об объявлении",
            operationId = "updateAds"
    )
    // todo 200, 401, 403, 404
    public Ad updateAds(
            @PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateAd createOrUpdateAd
    ) {
        return adService.updateAd(id, createOrUpdateAd);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получение объявлений авторизованного пользователя",
            operationId = "getAdsMe"
    )
    // todo 200, 401
    public Ads getAdsMe() {
        return adService.getAdsMe();
    }

    @PatchMapping("/{id}/image")
    @Operation(
            summary = "Обновление картинки объявления",
            operationId = "updateImage"
    )
    // todo 200, 401, 403, 404
    public void updateImage(
            @PathVariable Integer id,
            @RequestParam("image") MultipartFile image
    ) {
        adService.updateAdImage(id, image);
    }
}