package ru.skypro.homework.dto.create_update_dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUser {

    @Schema(description = "имя пользователя", minLength = 2, maxLength = 16)
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "фамилия пользователя", minLength = 2, maxLength = 16)
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}