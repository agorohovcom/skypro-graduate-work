package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Data
@Schema(description = "DTO для списка объявлений")
public class AdsDto {

    @Schema(description = "Список объявлений")
    private List<Ad> results;
}