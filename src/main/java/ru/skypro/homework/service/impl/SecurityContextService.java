package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ForbiddenException;

@Service
public class SecurityContextService {

    public UserEntity getCurrentUserEntity() {
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
}
