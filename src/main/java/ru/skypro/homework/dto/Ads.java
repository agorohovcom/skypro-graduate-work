package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.entity.Ad;

import java.util.List;

@Getter
@Setter
public class Ads {
    private List<Ad> results;
}