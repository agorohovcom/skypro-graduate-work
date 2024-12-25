package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO для создания или обновления объявления")
public class CreateOrUpdateAdDto {
    @Schema(description = "Заголовок объявления", minLength = 4, maxLength = 32)
    private String title;

    @Schema(description = "Цена объявления", minimum = "0", maximum = "10000000")
    private Integer price;

    @Schema(description = "Описание объявления", minLength = 8, maxLength = 64)
    private String description;
}
