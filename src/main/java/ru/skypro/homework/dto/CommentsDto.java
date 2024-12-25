package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Data
@Schema(description = "DTO для списка комментариев")
public class CommentsDto {

    @Schema(description = "Список комментариев")
    private List<Comment> results;
}