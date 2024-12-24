package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;

@RestController
@RequestMapping("/ads")
public class AdController {

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        // логика
        return ResponseEntity.ok(new Ads());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(
            @RequestParam("image") MultipartFile image,
            @RequestParam("properties") CreateOrUpdateAd properties) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Ad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Long id) {
        // логика
        return ResponseEntity.ok(new ExtendedAd());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateAd updateAd) {
        // логика
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        // логика
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateAdImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image) {
        // логика
        return ResponseEntity.ok(new byte[0]);
    }
}