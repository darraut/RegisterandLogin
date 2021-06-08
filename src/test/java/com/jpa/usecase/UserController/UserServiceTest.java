package com.jpa.usecase.UserController;

import com.jpa.usecase.controller.UserController;
import com.jpa.usecase.dto.UserDto;
import com.jpa.usecase.entities.Account;
import com.jpa.usecase.entities.LoginStatus;
import com.jpa.usecase.entities.User;
import com.jpa.usecase.service.UserRepository;
import com.jpa.usecase.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    static UserDto userDto;

    static User user;

    static Account account;

    @BeforeAll
    public static void setUp() {

        account = new Account();
        account.setAccountNo(1L);
        account.setBalance(20000.00);
        account.setBankName("ICICI");
        account.setBenificiryAccountList(null);
        account.setUser(user);
        user = new User();
        user.setAccount(account);
        user.setCountry("IN");
        user.setEmail("darshan@gmail.com");
        user.setLoginStatus(LoginStatus.Success);
        user.setPassword("Darshan@123");
        user.setUserId(1L);
        user.setUserName("Darshan95");

        userDto = new UserDto();
        userDto.setBalance(2000.00);
        userDto.setBankName("ICICI");
        userDto.setUser(user);

    }

    @Test
    @DisplayName("User Registration : Positive scenario")
    void signUpNewUser() {

        Mockito.when(userRepository.findOneByEmailAndUserName("darshan@gmail.com","Darshan95")).thenReturn(user);

        User result = userService.signUpNewUser(userDto);

        verify(userService).signUpNewUser(userDto);

        assertEquals(user, result);

    }


}
