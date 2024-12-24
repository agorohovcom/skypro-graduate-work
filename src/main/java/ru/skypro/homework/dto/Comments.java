package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Setter
@Getter
public class Comments {
    private List<Comment> results;
}