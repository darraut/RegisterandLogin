package com.jpa.usecase.UserController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.usecase.DTO.UserDTO;
import com.jpa.usecase.controller.UserController;
import com.jpa.usecase.entities.LoginStatus;
import com.jpa.usecase.entities.User;
import com.jpa.usecase.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;
    private UserController userController;




    @Test
    @DisplayName("Registration : Positive scenario")
    void signUpNewUser() throws Exception {
        UserDTO userDTO= new UserDTO();
        userDTO.setBankName("ICICI");
        userDTO.setBalance(2000.00);
        User newUser=new User(1L,"Darshan","Darshan@123","Darshan@123","IN",LoginStatus.Success);
        userDTO.setUser(newUser);
        ResponseEntity<User> entity =new ResponseEntity(HttpStatus.OK);
        String URI = "/api/user/signUp";
        Mockito.when(userService.signUpNewUser(any(UserDTO.class))).thenReturn(entity);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(mapToJson(newUser)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response
                = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    @DisplayName("Registration : Positive scenario")
    void signUpNewUser1() throws Exception {
        UserDTO userDTO= new UserDTO();
        userDTO.setBankName("ICICI");
        userDTO.setBalance(2000.00);
        User newUser=new User(1L,"Darshan","Darshan@123","Darshan@123","IN",LoginStatus.Success);
        userDTO.setUser(newUser);
        ResponseEntity<User> entity =new ResponseEntity(newUser,HttpStatus.OK);

        Mockito.when(userService.signUpNewUser(any())).thenReturn(entity);
        ResponseEntity<User>responseEntity=userService.signUpNewUser(userDTO);
        assertEquals(newUser,userDTO.getUser());
        assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.OK.value());

    }
    @Test
    @DisplayName("Login : Positive scenario")
    void loginUser() throws Exception {
        User user=new User("Darshan@gmail.com","Darshan@123");
        Mockito.when(userService.loginUser(any(),any())).thenReturn(user);
        User newUser= userService.loginUser("Darshan@gmail.com","Darshan@123");
        assertNotNull(newUser);
        assertEquals(user.getEmail(),newUser.getEmail());
        assertEquals(user.getPassword(),newUser.getPassword());
        assertEquals(user,newUser);
    }

    @Test
    @DisplayName("Login : Negative scenario")
    void loginUserFail() {
        User user=new User("","Darshan@123");
        Mockito.when(userService.loginUser(notNull(),notNull())).thenReturn(user);
        User newUser= userService.loginUser("","Darshan@123");
        assertSame(user,newUser);
    }







    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
