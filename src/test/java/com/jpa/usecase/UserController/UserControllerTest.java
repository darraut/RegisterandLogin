package com.jpa.usecase.UserController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.usecase.dto.UserDto;
import com.jpa.usecase.controller.UserController;
import com.jpa.usecase.entities.Account;
import com.jpa.usecase.entities.LoginStatus;
import com.jpa.usecase.entities.User;
import com.jpa.usecase.exception.EmailAndUserNameValidationException;
import com.jpa.usecase.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

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

        Mockito.when(userService.signUpNewUser(userDto)).thenReturn(user);

        ResponseEntity<UserDto> newUser = userController.signUpNewUser(userDto);

        verify(userService).signUpNewUser(userDto);

        assertEquals("Darshan95 Successfully Register", newUser.getBody());

    }

    @Test
    @DisplayName("User Registration : Negative scenario")
    void notSignUpNewUser() {


        Mockito.when(userService.signUpNewUser(userDto)).thenReturn(null);

        User newUser = userService.signUpNewUser(userDto);

        assertNull(newUser);


    }

    @Test
    @DisplayName("Login : Positive scenario")
    void loginUser() {

        String expectResult="Darshan95 is Login SuccessFully";
        Mockito.when(userService.loginUser("Darshan@gmail.com", "Darshan@123")).thenReturn(user);

        ResponseEntity<User> newUser = userController.loginUser("Darshan@gmail.com", "Darshan@123");

        assertEquals(expectResult,newUser.getBody());
    }

    @Test
    @DisplayName("Login : Negative scenario")
    void loginUserFail() {
        User user = new User("Darshan@gmail.com", "Darshan@123");
        Mockito.when(userService.loginUser(anyString(), anyString())).thenReturn(null);

        User newUser = userService.loginUser("Darshan@gmail.com", "Darshan@123");

        assertNull(newUser);
    }

    @Test
    @DisplayName("GetUserAccountById : Positive scenario")
    void getUserAccountById()  {

        Map<String,Object>userAccountMap = new HashMap<>();
        userAccountMap.put("accountNo",account.getAccountNo());
        userAccountMap.put("Balance",account.getBalance());
        userAccountMap.put("BankName",account.getBankName());
        userAccountMap.put("UserName",user.getUserName());
        userAccountMap.put("mail",user.getEmail());


        Mockito.when(userService.getAccount(1L)).thenReturn(userAccountMap);

        ResponseEntity<Account> userAccount=  userController.getAccount(1L);

        verify(userService).getAccount(1L);

        assertEquals(userAccountMap,userAccount.getBody());

    }
}





