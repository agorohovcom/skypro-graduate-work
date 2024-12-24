package ru.skypro.homework.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpdateAd {
    @Size(
            min = 4,
            max = 32,
            message = "Title must be between 4 and 32 characters"
    )
    private String title;

    @Min(
            value = 0,
            message = "Price must be at least 0"
    )
    @Max(
            value = 10000000,
            message = "Price must be at most 10,000,000"
    )
    private Integer price;

    @Size(
            min = 8,
            max = 64,
            message = "Description must be between 8 and 64 characters"
    )
    private String description;
}
