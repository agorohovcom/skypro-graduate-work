package ru.skypro.homework.dto.security_dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Login {

    @Schema(description = "пароль", minLength = 8, maxLength = 16)
    @Size(min = 8, max = 16)
    private String password;
    @Schema(description = "логин", minLength = 4, maxLength = 32)
    @Size(min = 4, max = 32)
    private String username;
}
