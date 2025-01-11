package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.security_dto.NewPassword;
import ru.skypro.homework.dto.create_update_dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = UserController.class)
//// todo нужно убрать это и использовать @WithMockUser
//@AutoConfigureMockMvc(addFilters = false)
class UserControllerWebMvcTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void setPassword_SuccessTest() throws Exception {
//        NewPassword newPassword = new NewPassword();
//        newPassword.setCurrentPassword("old_password");
//        newPassword.setNewPassword("new_password");
//
//        mockMvc.perform(patch("/users/set_password")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newPassword)))
//                .andExpect(status().isOk());
//
//        verify(userService, times(1)).setPassword(newPassword);
//    }
//
//    @Test
//    public void setPassword_ValidationFailedTest() throws Exception {
//        NewPassword newPassword = new NewPassword();
//        newPassword.setCurrentPassword("short");
//        newPassword.setNewPassword("new_password");
//
//        mockMvc.perform(patch("/users/set_password")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newPassword)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void setPassword_UnauthorizedTest() throws Exception {
//        NewPassword newPassword = new NewPassword();
//        newPassword.setCurrentPassword("old_password");
//        newPassword.setNewPassword("new_password");
//
//        doThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Пользователь не авторизован"))
//                .when(userService)
//                .setPassword(any(NewPassword.class));
//
//        mockMvc.perform(patch("/users/set_password")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newPassword)))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void getUserTest_Success() throws Exception {
//        User authenticatedUser = new User();
//        authenticatedUser.setId(1);
//        authenticatedUser.setFirstName("Barbara");
//        authenticatedUser.setLastName("Liskov");
//
//        when(userService.getUser()).thenReturn(authenticatedUser);
//
//        mockMvc.perform(get("/users/me"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.firstName").value("Barbara"))
//                .andExpect(jsonPath("$.lastName").value("Liskov"));
//
//        verify(userService, times(1)).getUser();
//    }
//
//    @Test
//    public void getUserTest_Unauthorized() throws Exception {
//        when(userService.getUser()).thenReturn(null);
//
//        mockMvc.perform(get("/users/me"))
//                .andExpect(status().isUnauthorized());
//
//        verify(userService, times(1)).getUser();
//    }
//
//    @Test
//    void updateUserTest_Success() throws Exception {
//        UpdateUser updateUserDto = new UpdateUser();
//        updateUserDto.setFirstName("Barbara");
//        updateUserDto.setLastName("Liskov");
//
//        User autentificatedUser = new User();
//        autentificatedUser.setId(1);
//
//        when(userService.getUser()).thenReturn(autentificatedUser);
//        when(userService.updateUser(any(UpdateUser.class))).thenReturn(updateUserDto);
//
//        mockMvc.perform(patch("/users/me")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateUserDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName").value("Barbara"))
//                .andExpect(jsonPath("$.lastName").value("Liskov"));
//    }
//
//    @Test
//    public void testUpdateUser_Unauthorized() throws Exception {
//        when(userService.getUser()).thenReturn(null);
//
//        mockMvc.perform(patch("/users/me")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(new UpdateUser())))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testUpdateUser_BadRequest() throws Exception {
//        UpdateUser updateUserDto = null;
//        User authenticatedUser = new User();
//        authenticatedUser.setId(1);
//
//        when(userService.getUser()).thenReturn(authenticatedUser);
//        when(userService.updateUser(any(UpdateUser.class))).thenReturn(null);
//
//        mockMvc.perform(patch("/users/me")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateUserDto)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testUpdateUserImage_Success() throws Exception {
//        User authenticatedUser = new User();
//        authenticatedUser.setId(1);
//
//        when(userService.getUser()).thenReturn(authenticatedUser);
//
//        MockMultipartFile image = new MockMultipartFile(
//                "image",
//                "test.jpg",
//                MediaType.IMAGE_JPEG_VALUE,
//                "test image content".getBytes()
//        );
//
//        mockMvc.perform(multipart("/users/me/image")
//                        .file(image)
//                        .with(request -> {
//                            request.setMethod("PATCH");
//                            return request;
//                        })
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testUpdateUserImage_Unauthorized() throws Exception {
//        when(userService.getUser()).thenReturn(null);
//
//        MockMultipartFile image = new MockMultipartFile(
//                "image",
//                "test.jpg",
//                MediaType.IMAGE_JPEG_VALUE,
//                "test image content".getBytes()
//        );
//
//        mockMvc.perform(multipart("/users/me/image")
//                        .file(image)
//                        .with(request -> {
//                            request.setMethod("PATCH");
//                            return request;
//                        })
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isUnauthorized());
//    }
}