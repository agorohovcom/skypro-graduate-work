package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void setPassword(NewPasswordDto newPasswordDto) {

    }

    @Override
    public User getUser() {
        return new User();
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        if (updateUserDto == null) {
            return null;
        }
        return new UpdateUserDto();
    }

    @Override
    public void updateUserImage(MultipartFile image) {

    }
}
