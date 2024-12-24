package ru.skypro.homework.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    @Size(
            min = 8,
            max = 16,
            message = "Password must be between 8 and 16 characters"
    )
    private String password;

    @Size(
            min = 4,
            max = 32,
            message = "Username must be between 4 and 32 characters"
    )
    private String username;
}
