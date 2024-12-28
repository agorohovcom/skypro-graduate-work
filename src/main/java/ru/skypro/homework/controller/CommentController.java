package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads/{id}/comments")
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "API для работы с комментариями")
public class CommentController {

    // todo сервис ещё не реализован
    private final CommentService commentService;

    @GetMapping
    @Operation(summary = "Получение комментариев объявления")
    public Comments getComments(@PathVariable Long id) {
//        return commentService.getComments(id);
        return new Comments();
    }

    @PostMapping
    @Operation(summary = "Добавление комментария к объявлению")
    public Long addComment(@PathVariable Long id, @RequestBody CreateOrUpdateComment comment) {
//        return commentService.addComment(id, comment);
        return 1L;
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удаление комментария")
    public void deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
//        commentService.deleteComment(id, commentId);
    }

    @PatchMapping("/{commentId}")
    @Operation(summary = "Обновление комментария")
    public Comment updateComment(
            @PathVariable Long id,
            @PathVariable Long commentId,
            @RequestBody CreateOrUpdateComment comment) {
//        return commentService.updateComment(id, commentId, comment);
        return new Comment();
    }
}