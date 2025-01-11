package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.security_dto.NewPassword;
import ru.skypro.homework.dto.create_update_dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {

    void setPassword(NewPassword newPassword);

    User getUser();

    UpdateUser updateUser(UpdateUser updateUser);

    void updateUserImage(MultipartFile image);
}
