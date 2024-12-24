package ru.skypro.homework.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateOrUpdateComment {
    @Size(
            min = 8,
            max = 64,
            message = "Text must be between 8 and 64 characters"
    )
    private String text;
}