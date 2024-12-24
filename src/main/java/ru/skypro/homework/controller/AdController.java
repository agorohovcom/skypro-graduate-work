package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;

@RestController
@RequestMapping("/ads")
public class AdController {

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        // Логика получения всех объявлений
        return ResponseEntity.ok(new Ads());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(
            @RequestParam("image") MultipartFile image,
            @RequestParam("properties") CreateOrUpdateAd properties) {
        // Логика добавления объявления
        return ResponseEntity.status(HttpStatus.CREATED).body(new Ad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Long id) {
        // Логика получения информации об объявлении
        return ResponseEntity.ok(new ExtendedAd());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        // Логика удаления объявления
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateAd updateAd) {
        // Логика обновления объявления
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        // Логика получения объявлений авторизованного пользователя
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateAdImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image) {
        // Логика обновления картинки объявления
        return ResponseEntity.ok(new byte[0]);
    }
}