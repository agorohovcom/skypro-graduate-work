package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.create_update_dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public Comments getComments(Integer id) {
        return new Comments();
    }

    @Override
    public Long addComment(Integer id, CreateOrUpdateComment comment) {
        return 1L;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {

    }

    @Override
    public Comment updateComment(Integer id, Integer commentId, CreateOrUpdateComment comment) {
        return new Comment();
    }
}
