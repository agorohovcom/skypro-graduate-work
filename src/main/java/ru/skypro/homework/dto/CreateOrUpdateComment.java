package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOrUpdateComment {

    @NotBlank
    @Schema(description = "текст комментария", minLength = 8, maxLength = 64)
    @Size(min = 8, max = 64)
    private String text;
}