package by.artem.integration.http.controller;

import by.artem.annotation.IT;

import static by.artem.spring.dto.UserCreateEditDto.Fields.*;

import by.artem.spring.database.entity.Role;
import by.artem.spring.dto.UserCreateEditDto;
import by.artem.spring.dto.UserReadDto;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UserControllerIT {

    private final MockMvc mockMvc;
    private final UserService userService;


    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "OPERATOR"})
    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users").with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER", "OPERATOR"})
    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .with(csrf())
                        .param(username, "test@gmail.com")
                        .param(password, "test")
                        .param(firstname, "Test")
                        .param(lastname, "TestTest")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                        .param(birthDate, "01-01-2000")
                        .param(image, "test")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }

    //TODO ошибка валидации в UserCreateEditDto (MultipartFile)
    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER", "OPERATOR"})
    @Test
    void update() throws Exception {
        mockMvc.perform(post("/users/1/update")
                        .with(csrf())
                        .param("id", "1")
                        .param(firstname, "Test")
                        .param(lastname, "TestTest")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                        .param(birthDate, "01-01-2000")
                        .param(image, "test.jpg")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }

    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "OPERATOR"})
    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/users/1")
                        .with(csrf())
                        .param("id", "1"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("companies"));
    }


    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/users/registration")
                        .with(csrf())
                        .param("user", "user")
                )
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("companies"));
    }

    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN"})
    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/users/1/delete")
                        .with(csrf())
                        .param("id", "1"))
                .andExpect(redirectedUrl("/users"));
    }
}
