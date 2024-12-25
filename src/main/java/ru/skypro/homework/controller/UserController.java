package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.User;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для работы с пользователями")
public class UserController {

    // todo сервиса ещё нет
//    private final UserService userService;

    @PatchMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public void setPassword(@RequestBody NewPasswordDto newPassword) {
//        userService.setPassword(newPassword);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public User getUser() {
//        return userService.getUser();
        return new User();
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUser) {
//        return userService.updateUser(updateUserDto);
        return updateUser;
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    public void updateUserImage(@RequestParam("image") MultipartFile image) {
//        userService.updateUserImage(image);
    }
}