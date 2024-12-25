package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO для обновления пароля")
public class NewPasswordDto {
    @Schema(description = "Текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;

    @Schema(description = "Новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;
}