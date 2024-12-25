package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO для создания или обновления комментария")
public class CreateOrUpdateCommentDto {
    @Schema(description = "Текст комментария", minLength = 8, maxLength = 64)
    private String text;
}