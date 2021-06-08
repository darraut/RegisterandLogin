package com.jpa.usecase.UserController;


import com.jpa.usecase.controller.UserController;
import com.jpa.usecase.dto.UserDto;
import com.jpa.usecase.entities.Account;
import com.jpa.usecase.entities.LoginStatus;
import com.jpa.usecase.entities.User;
import com.jpa.usecase.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {


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

        Mockito.when(userService.signUpNewUser(userDto)).thenReturn(user);

        ResponseEntity<User> newUser = userController.signUpNewUser(userDto);

        verify(userService).signUpNewUser(userDto);

        assertEquals(user, newUser.getBody());

    }


    @Test
    @DisplayName("Login : Positive scenario")
    void loginUser() {

        Mockito.when(userService.loginUser("Darshan@gmail.com", "Darshan@123")).thenReturn(user);

        ResponseEntity<User> newUser = userController.loginUser("Darshan@gmail.com", "Darshan@123");

        assertEquals(user,newUser.getBody());
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





