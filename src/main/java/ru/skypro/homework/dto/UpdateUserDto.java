package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data

@Schema(description = "DTO для обновления информации о пользователе")
public class UpdateUserDto {

    @Schema(description = "Имя пользователя", minLength = 2, maxLength = 16)
    private String firstName;

    @Schema(description = "Фамилия пользователя", minLength = 2, maxLength = 16)
    private String lastName;

    @Schema(description = "Телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}