package com.jpa.usecase.UserController;

import ch.qos.logback.core.boolex.EvaluationException;
import com.jpa.usecase.dto.UserDto;
import com.jpa.usecase.entities.Account;
import com.jpa.usecase.entities.LoginStatus;
import com.jpa.usecase.entities.User;
import com.jpa.usecase.exception.EmailAndUserNameValidationException;
import com.jpa.usecase.exception.GlobalException;
import com.jpa.usecase.service.UserRepository;
import com.jpa.usecase.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

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
        user.setLoginStatus(LoginStatus.success);
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
        User user1 = new User();
        user1.setAccount(account);
        user1.setCountry("IN");
        user1.setEmail("Vijay@gmail.com");
        user1.setLoginStatus(LoginStatus.success);
        user1.setPassword("Vjiay@123");
        user1.setUserId(1L);
        user1.setUserName("Vijay95");

        Mockito.when(userRepository.findOneByEmailAndUserName(user.getEmail(), user.getUserName())).thenReturn(user1);

        User result = userService.signUpNewUser(userDto);

        verify(userRepository).findOneByEmailAndUserName(user.getEmail(), user.getUserName());

        assertNotEquals(user1,result);

    }

    @DisplayName("User Registration : Exception")
    @Test
    void signUpNewUserFail() {
        User user1 = new User();
        user1.setAccount(account);
        user1.setCountry("IN");
        user1.setEmail("Vijay@gmail.com");
        user1.setLoginStatus(LoginStatus.success);
        user1.setPassword("Vjiay@123");
        user1.setUserId(1L);
        user1.setUserName("Vijay95");


        Mockito.when(userRepository.findOneByEmailAndUserName(user.getEmail(),user.getUserName())).thenThrow(new EmailAndUserNameValidationException());

        Exception ex = assertThrows(EmailAndUserNameValidationException.class, () -> userService.signUpNewUser(userDto));

    }


}
