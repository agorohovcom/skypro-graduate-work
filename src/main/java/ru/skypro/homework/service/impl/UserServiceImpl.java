package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.create_update_dto.UpdateUser;
import ru.skypro.homework.dto.security_dto.NewPassword;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AppMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AppMapper appMapper;

    @Override
    public void setPassword(String username, NewPassword newPassword) {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Нет пользователя с username: " + username));
        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Нет пользователя с username: " + username));
        return appMapper.userEntityToUser(user);
    }

    @Override
    public UpdateUser updateUser(String username, UpdateUser updateUser) {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Нет пользователя с username: " + username));
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
        return appMapper.userEntityToUpdateUser(user);

    }

    @Override
    public void updateUserImage(MultipartFile image) {

    }
}
