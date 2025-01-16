package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.skypro.homework.enums.Role;

@Data
public class User {

    @Schema(description = "id пользователя")
    private Integer id;
    @Schema(description = "логин пользователя")
    private String email;
    @Schema(description = "имя пользователя")
    private String firstName;
    @Schema(description = "фамилия пользователя")
    private String lastName;
    @Schema(description = "телефон пользователя")
    private String phone;
    @Enumerated(EnumType.STRING)
    @Schema(description = "роль пользователя")
    private Role role;
    @Schema(description = "ссылка на аватар пользователя")
    private String image;
}