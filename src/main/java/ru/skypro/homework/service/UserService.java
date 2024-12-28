package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {

    void setPassword(NewPassword newPasswordDto);

    User getUser();

    UpdateUser updateUser(UpdateUser updateUserDto);

    void updateUserImage(MultipartFile image);
}