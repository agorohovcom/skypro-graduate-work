package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.create_update_dto.UpdateUser;
import ru.skypro.homework.dto.security_dto.NewPassword;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.enums.Role;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.MyUserPrincipal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    UserEntity anotherUser;
    UserEntity user;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();

        user = new UserEntity();
        user.setEmail("test@example.com");
        user.setPassword("oldPassword");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPhone("+7(999)123-45-67");
        user.setRole(Role.USER);
        userRepository.save(user);

        MyUserPrincipal principal = new MyUserPrincipal(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        anotherUser = new UserEntity();
        anotherUser.setEmail("another_user@example.com");
        anotherUser.setPassword("another_user_password");
        anotherUser.setFirstName("another_user_first_name");
        anotherUser.setLastName("another_user_last_name");
        anotherUser.setPhone("+7(999)000-00-00");
        anotherUser.setRole(Role.USER);
        userRepository.save(anotherUser);
    }

    @Test
    @WithMockUser(username = "test@example.com", password = "password")
    public void testSetPassword_Success() {
        NewPassword newPassword = new NewPassword();
        newPassword.setNewPassword("newPassword");

        userService.setPassword("test@example.com", newPassword);

        UserEntity updatedUser = userRepository.findByEmail("test@example.com").orElseThrow();
        assertNotEquals("oldPassword", updatedUser.getPassword());
        assertTrue(passwordEncoder.matches("newPassword", updatedUser.getPassword()));
    }

    @Test
    @WithMockUser(username = "unknown@example.com", password = "password")
    public void testSetPassword_UserNotFound() {
        NewPassword newPassword = new NewPassword();
        newPassword.setNewPassword("newPassword");

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.setPassword("unknown@example.com", newPassword);
        });
    }

    @Test
    @WithMockUser(username = "test@example.com", password = "password")
    public void testGetUser_Success() {
        User result = userService.getUser("test@example.com");

        assertNotNull(result);
        assertEquals("Test", result.getFirstName());
        assertEquals("User", result.getLastName());
    }

    @Test
    @WithMockUser(username = "unknown@example.com", password = "password")
    public void testGetUser_UserNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUser("unknown@example.com");
        });
    }

    @Test
//    @WithMockUser(username = "anonymous", roles = {"ANONYMOUS"})
    public void testGetUser_Unauthorized() {
        SecurityContextHolder.clearContext();
        assertThrows(ForbiddenException.class, () -> {
            userService.getUser("another_user@example.com");
        });
    }

    @Test
    @WithMockUser(username = "test@example.com", password = "password")
    public void testUpdateUser_Success() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName("Updated");
        updateUser.setLastName("User");
        updateUser.setPhone("+7(999)987-65-43");

        UpdateUser result = userService.updateUser("test@example.com", updateUser);

        assertNotNull(result);
        assertEquals("Updated", result.getFirstName());
        assertEquals("User", result.getLastName());
        assertEquals("+7(999)987-65-43", result.getPhone());

        UserEntity updatedUser = userRepository.findByEmail("test@example.com").orElseThrow();
        assertEquals("Updated", updatedUser.getFirstName());
        assertEquals("User", updatedUser.getLastName());
        assertEquals("+7(999)987-65-43", updatedUser.getPhone());
    }

    @Test
    @WithMockUser(username = "unknown@example.com", password = "password")
    public void testUpdateUser_UserNotFound() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName("Updated");
        updateUser.setLastName("User");
        updateUser.setPhone("0987654321");

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.updateUser("unknown@example.com", updateUser);
        });
    }

    @Test
    @WithMockUser(username = "another@example.com", password = "password")
    public void testUpdateUser_Forbidden() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName("Updated");
        updateUser.setLastName("User");
        updateUser.setPhone("+7(999)987-65-43");

        assertThrows(ForbiddenException.class, () -> {
            userService.updateUser("another_user@example.com", updateUser);
        });
    }

    @Test
    @WithMockUser(username = "test@example.com", password = "password")
    public void testUpdateUserImage() {
        // Этот метод пока не реализован, поэтому просто проверяем, что он не падает
        assertDoesNotThrow(() -> {
            userService.updateUserImage(null);
        });
    }
}