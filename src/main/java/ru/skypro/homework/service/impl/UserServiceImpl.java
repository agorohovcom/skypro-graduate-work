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
    private final SecurityContextService securityContextService;

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
        validRulesCheck(securityContextService.getCurrentUserEntity());
    }

    private void validRulesCheck(UserEntity userEntity) {
        UserEntity userEntityFromContext = securityContextService.getCurrentUserEntity();
        boolean valid = userEntity.getId().equals(userEntityFromContext.getId())
                || userEntityFromContext.getRole().name().equals(Role.ADMIN.name());
        if (!valid) {
            throw new ForbiddenException(
                    "Нет прав доступа к пользователю с логином: " + userEntity.getEmail());
        }
    }
}
