package ru.skypro.homework.dto.security_dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.skypro.homework.enums.Role;

@Data
public class Register {

    @Schema(description = "логин", minLength = 4, maxLength = 32)
    @Size(min = 4, max = 32)
    private String username;
    @Schema(description = "пароль", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16)
    private String password;
    @Schema(description = "имя пользователя", minLength = 2, maxLength = 16)
    @Size(min = 2, max = 16)
    private String firstName;
    @Schema(description = "фамилия пользователя", minLength = 2, maxLength = 16)
    @Size(min = 2, max = 16)
    private String lastName;
    @Schema(description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    @Enumerated(EnumType.STRING)
    @Schema(description = "роль пользователя")
    private Role role;
}