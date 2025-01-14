package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.create_update_dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads/{id}/comments")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {

    // todo сервис ещё не реализован
    private final CommentService commentService;

    @GetMapping
    @Operation(
            summary = "Получение комментариев объявления",
            operationId = "getComments"
    )
    // todo 200, 401, 404
    public Comments getComments(@PathVariable Integer id) {
        return commentService.getComments(id);
    }

    @PostMapping
    @Operation(
            summary = "Добавление комментария к объявлению",
            operationId = "addComment"
    )
    // todo 200, 401, 404
    public Long addComment(
            @PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateComment comment) {
        return commentService.addComment(id, comment);
    }

    @DeleteMapping("/{commentId}")
    @Operation(
            summary = "Удаление комментария",
            operationId = "deleteComment"
    )
    // todo 204, 401, 403, 404
    public void deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId
    ) {
        commentService.deleteComment(adId, commentId);
    }

    @PatchMapping("/{commentId}")
    @Operation(
            summary = "Обновление комментария",
            operationId = "updateComment"
    )
    // todo 200, 401, 403, 404
    public Comment updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @Valid @RequestBody CreateOrUpdateComment comment) {
        return commentService.updateComment(adId, commentId, comment);
    }
}