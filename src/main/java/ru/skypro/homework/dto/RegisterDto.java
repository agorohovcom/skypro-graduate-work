package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Schema(description = "DTO для регистрации пользователя")
public class RegisterDto {
    @Schema(description = "Логин", minLength = 4, maxLength = 32)
    private String username;

    @Schema(description = "Пароль", minLength = 8, maxLength = 16)
    private String password;

    @Schema(description = "Имя пользователя", minLength = 2, maxLength = 16)
    private String firstName;

    @Schema(description = "Фамилия пользователя", minLength = 2, maxLength = 16)
    private String lastName;

    @Schema(description = "Телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Роль пользователя")
    private Role role;
}