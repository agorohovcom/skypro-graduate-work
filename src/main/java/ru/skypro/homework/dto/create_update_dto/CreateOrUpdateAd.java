package ru.skypro.homework.dto.create_update_dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOrUpdateAd {

    @Schema(description = "заголовок объявления", minLength = 4, maxLength = 32)
    @Size(min = 4, max = 32)
    private String title;
    @Schema(description = "цена объявления", minimum = "0", maximum = "10000000")
    @Size(max = 10000)
    private Integer price;
    @Schema(description = "описание объявления", minLength = 8, maxLength = 64)
    @Size(min = 8, max = 64)
    private String description;
}
