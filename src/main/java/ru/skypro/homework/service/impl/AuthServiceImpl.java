package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.security_dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AppMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AppMapper mapper;
    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder encoder;

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        UserEntity findUser = userRepository.findByEmail(register.getUsername());
        if (findUser != null) {
            return false;
        }
        register.setPassword(encoder.encode(register.getPassword()));
        userRepository.save(mapper.registerToUserEntity(register));
        return true;
    }
}
