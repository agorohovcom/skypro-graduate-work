package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для работы с пользователями")
public class UserController {

    // todo сервис ещё не реализован
    private final UserService userService;

    @PatchMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public void setPassword(@RequestBody NewPasswordDto newPassword) {
        userService.setPassword(newPassword);
    }

    // todo как понимать условие
    //  "При выполнении цикла CRUD-операций (POST, GET, PUT, DELETE) не возвращается статус ответа 200."?
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ResponseStatus(HttpStatus.OK)
    public User getUser() {
        User authenticateduser = userService.getUser();

        if (authenticateduser != null) {
            return authenticateduser;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Пользователь не авторизован");
        }
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserDto updateUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        User authenticatedUser = getUser();

        UpdateUserDto result = userService.updateUser(updateUserDto);
        if (updateUserDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь не найден или ошибка валидации");
        }

        return result;
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserImage(@RequestParam("image") MultipartFile image) {
        User autentificatedUser = getUser();
        userService.updateUserImage(image);
    }
}