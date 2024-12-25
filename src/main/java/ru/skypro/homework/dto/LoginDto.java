package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO для авторизации пользователя")
public class LoginDto {

    @Schema(description = "Пароль", minLength = 8, maxLength = 16)
    private String password;

    @Schema(description = "Логин", minLength = 4, maxLength = 32)
    private String username;
}
