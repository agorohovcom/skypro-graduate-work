package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.create_update_dto.UpdateUser;
import ru.skypro.homework.dto.security_dto.NewPassword;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.enums.Role;
import ru.skypro.homework.exception.ForbiddenException;
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
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Нет пользователя с username: " + username));
        validRulesCheck(userEntity);
        userEntity.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public User getUser(String username) {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Нет пользователя с username: " + username));
        validRulesCheck(userEntity);
        return appMapper.userEntityToUser(userEntity);
    }

    @Override
    public UpdateUser updateUser(String username, UpdateUser updateUser) {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Нет пользователя с username: " + username));
        validRulesCheck(userEntity);
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return appMapper.userEntityToUpdateUser(userEntity);

    }

    @Override
    public void updateUserImage(MultipartFile image) {
        validRulesCheck(getCurrentUserEntity());
    }

    // Метод для получения текущего пользователя
    private UserEntity getCurrentUserEntity() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            throw new ForbiddenException("Пользователь не найден, так как контекст безопасности недоступен");
        }
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            throw new ForbiddenException("Пользователь не аутентифицирован");
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof MyUserPrincipal)) {
            throw new UsernameNotFoundException("Пользователь не найден или не соответствует типу MyUserPrincipal");
        }

        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return myUserPrincipal.getUser();
    }

    private void validRulesCheck(UserEntity userEntity) {
        boolean valid = userEntity.getId().equals(getCurrentUserEntity().getId())
                || getCurrentUserEntity().getRole().name().equals(Role.ADMIN.name());
        if (!valid) {
            throw new ForbiddenException(
                    "Нет прав доступа к пользователю с логином: " + userEntity.getEmail());
        }
    }
}
