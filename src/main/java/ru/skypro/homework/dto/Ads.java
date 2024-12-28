package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Ads {
    private List<Ad> results;
}