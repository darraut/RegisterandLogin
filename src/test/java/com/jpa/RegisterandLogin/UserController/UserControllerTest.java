package com.jpa.RegisterandLogin.UserController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.RegisterandLogin.DTO.UserDTO;
import com.jpa.RegisterandLogin.entities.LoginStatus;
import com.jpa.RegisterandLogin.entities.User;
import com.jpa.RegisterandLogin.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Registration : Positive scenario")
    void signUpNewUser() throws Exception {
        UserDTO userDTO= new UserDTO();
        userDTO.setBankName("ICICI");
        userDTO.setBalance(2000.00);
        User newUser=new User(1L,"Darshan","Darshan@123","Darshan@123","IN",null,LoginStatus.Success);
        userDTO.setUser(newUser);
        ResponseEntity<User> entity =new ResponseEntity(HttpStatus.OK);
        String inputInJson = this.mapToJson(newUser);
        String URI = "/api/user/signUp";
        Mockito.when(userService.signUpNewUser(Mockito.any(UserDTO.class))).thenReturn(entity);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    @DisplayName("Login : Positive scenario")
    void loginUser() throws Exception {
        User user = new User("Ajay@gmail.com","Ajay@123");
        ResponseEntity<User> entity =new ResponseEntity(HttpStatus.OK);
        String inputInJson = this.mapToJson(user);
        String URI = "/api/user/login";
        Mockito.when(userService.loginUser(user.getEmail(),user.getPassword())).thenReturn(entity);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }




    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
