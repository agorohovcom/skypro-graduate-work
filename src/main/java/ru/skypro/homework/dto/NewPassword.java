package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewPassword {
    @Schema(description = "текущий пароль", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String currentPassword;

    @Schema(description = "новый пароль", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String newPassword;
}