package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.create_update_dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getComments(Integer id);

    Long addComment(Integer id, CreateOrUpdateComment comment);

    void deleteComment(Integer adId, Integer commentId);

    Comment updateComment(Integer id, Integer commentId, CreateOrUpdateComment comment);
}
