package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.create_update_dto.UpdateUser;
import ru.skypro.homework.dto.security_dto.NewPassword;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")
public class UserController {

    // todo сервис ещё не реализован
    private final UserService userService;

    @PostMapping("/set_password")
    @Operation(
            summary = "Обновление пароля",
            operationId = "setPassword"
    )
    // todo 200, 401
    public void setPassword(@Valid @RequestBody NewPassword newPassword,
                            Authentication authentication) {
        String userName = authentication.getName();
        userService.setPassword(userName, newPassword);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            operationId = "getUser"
    )
    // todo 200, 401
    public User getUser(Authentication authentication) {
        String username = authentication.getName();
        User authenticatedUser = userService.getUser(username);
        if (authenticatedUser != null) {
            return authenticatedUser;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Пользователь не авторизован");
        }
    }

    @PatchMapping("/me")
    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            operationId = "updateUser"
    )
    // todo 200, 401
    public UpdateUser updateUser(@Valid @RequestBody UpdateUser updateUser,
                                 Authentication authentication) {
        String username = authentication.getName();
        return userService.updateUser(username, updateUser);
    }

    @PatchMapping("/me/image")
    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            operationId = "updateUserImage"
    )
    // todo 200, 401
    public void updateUserImage(@RequestParam("image") MultipartFile image) {
        userService.updateUserImage(image);
    }
}