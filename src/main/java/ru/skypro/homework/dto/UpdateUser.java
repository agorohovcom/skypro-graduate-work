package ru.skypro.homework.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUser {
    @Size(
            min = 2,
            max = 16,
            message = "First name must be between 2 and 16 characters")
    private String firstName;

    @Size(
            min = 2,
            max = 16,
            message = "Last name must be between 2 and 16 characters")
    private String lastName;

    @Pattern(
            regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Phone must match the pattern +7 (XXX) XXX-XX-XX"
    )
    private String phone;
}