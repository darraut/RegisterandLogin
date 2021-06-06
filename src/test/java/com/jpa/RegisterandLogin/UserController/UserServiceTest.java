package com.jpa.RegisterandLogin.UserController;

import com.jpa.RegisterandLogin.controller.UserController;
import com.jpa.RegisterandLogin.service.UserRepository;
import com.jpa.RegisterandLogin.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;
    private UserService userService;


    /*@Test
    @DisplayName("Registration : Positive scenario")
    void signUpNewUser() throws Exception {

        userRepository.findOneByEmailAndUserName()
    }*/
}
