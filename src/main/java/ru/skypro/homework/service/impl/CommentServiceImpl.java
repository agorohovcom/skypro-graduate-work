package ru.skypro.homework.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.create_update_dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.enums.Role;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.AppMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final AdsRepository adsRepository;
    private final AppMapper appMapper;

    @Override
    public Comments getComments(Integer id) {
        AdEntity adEntity = findAd(id);
        List<CommentEntity> commentsEntity = adEntity.getComments();
        List<Comment> listOfComments = commentsEntity.stream()
                .map(appMapper::commentEntityToComment)
                .toList();
        Comments comments = new Comments();
        comments.setCount(listOfComments.size());
        comments.setResults(listOfComments);
        return comments;
    }

    @Override
    @Transactional
    public Comment addComment(Integer id, CreateOrUpdateComment comment) {
        LocalDateTime createAt = LocalDateTime.now();
        AdEntity adEntity = findAd(id);
        CommentEntity commentEntity = appMapper.createOrUpdateCommentToCommentEntity(
                comment, adEntity, getCurrentUserEntity(), createAt);
        adEntity.getComments().add(commentEntity);
        adsRepository.save(adEntity);
        return appMapper.commentEntityToComment(commentEntity);
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId) {
        CommentEntity commentEntity = findComment(adId, commentId);
        validRulesCheck(commentEntity);
        AdEntity adEntity = findAd(adId);
        adEntity.getComments().remove(commentEntity);
    }

    @Override
    @Transactional
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment) {
        CommentEntity commentEntity = findComment(adId, commentId);
        validRulesCheck(commentEntity);
        appMapper.updateCommentEntityFromDto(createOrUpdateComment, commentEntity);
        return appMapper.commentEntityToComment(commentEntity);
    }

    private UserEntity getCurrentUserEntity() {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return myUserPrincipal.getUser();
    }

    private CommentEntity findComment(Integer adId, Integer commentId) {
        AdEntity adEntity = findAd(adId);
        List<CommentEntity> listOfComments = adEntity.getComments();
            return listOfComments.stream()
                    .filter(commentEntity -> (Objects.equals(commentEntity.getId(), commentId)))
                    .findFirst().orElseThrow(() -> new CommentNotFoundException(
                            "Не найден комментарий с id: " + commentId));
    }

    private AdEntity findAd(Integer adId) {
        return adsRepository.findById(adId).orElseThrow(() ->
                new AdNotFoundException("Не найдено объявление с id: " + adId));
    }

    private void validRulesCheck(CommentEntity commentEntity) {
        boolean valid = commentEntity.getAuthor().getId().equals(getCurrentUserEntity().getId())
                || getCurrentUserEntity().getRole().name().equals(Role.ADMIN.name());
        if (!valid) {
            throw new ForbiddenException("Нет прав для редактирования комментария с id: " + commentEntity.getId());
        }
    }
}

