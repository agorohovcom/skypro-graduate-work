package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.User;

public interface UserService {

    void setPassword(NewPasswordDto newPasswordDto);

    User getUser();

    UpdateUserDto updateUser(UpdateUserDto updateUserDto);

    void updateUserImage(MultipartFile image);
}
