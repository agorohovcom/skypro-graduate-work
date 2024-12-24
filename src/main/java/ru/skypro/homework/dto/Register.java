package ru.skypro.homework.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Register {
    @Size(min = 4, max = 32)
    private String username;

    @Size(min = 8, max = 16)
    private String password;

    @Size(min = 2, max = 16)
    private String firstName;

    @Size(min = 2, max = 16)
    private String lastName;

    @Pattern(
            regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Phone must match the pattern +7 (XXX) XXX-XX-XX"
    )
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }
}