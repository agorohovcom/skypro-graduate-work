package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.create_update_dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.create_update_dto.UpdateUser;
import ru.skypro.homework.dto.security_dto.Register;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", imports = {Instant.class, LocalDateTime.class, ZoneId.class})
public interface AppMapper {

    @Mapping(source = "username", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "role", target = "role")
    UserEntity registerToUserEntity(Register register);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "image", target = "image")
    User userEntityToUser(UserEntity userEntity);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    UpdateUser userEntityToUpdateUser(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", source = "author")
    AdEntity createOrUpdateToAdEntity(CreateOrUpdateAd createOrUpdateAd, UserEntity author);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    Ad adEntitytoAd(AdEntity adEntity);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    ExtendedAd adEntitytoExtendedAd(AdEntity adEntity);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(target = "createdAt", expression =
            "java(commentEntity.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())")
    @Mapping(source = "text", target = "text")
    Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "authorImage", target = "author.image")
    @Mapping(target = "createdAt", expression =
            "java(LocalDateTime.ofInstant(Instant.ofEpochMilli(comment.getCreatedAt()), ZoneId.systemDefault()))")
    @Mapping(source = "text", target = "text")
    CommentEntity commentToCommentEntity(Comment comment);

    // это метод именно для обновления существующего объявления с помощью @MappingTarget
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", ignore = true)
    void updateAdEntityFromDto(CreateOrUpdateAd createOrUpdateAd, @MappingTarget AdEntity adEntity);
}