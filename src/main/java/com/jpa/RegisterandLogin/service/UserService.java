package com.jpa.RegisterandLogin.service;


import java.util.*;

import javax.validation.Valid;

import com.jpa.RegisterandLogin.DTO.UserDTO;
import com.jpa.RegisterandLogin.entities.Account;
import com.jpa.RegisterandLogin.entities.LoginStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;


import com.jpa.RegisterandLogin.entities.User;
import com.jpa.RegisterandLogin.exception.EmailAndUserNameValidationException;
import com.jpa.RegisterandLogin.exception.UserNotFoundException;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public ResponseEntity<User> signUpNewUser(UserDTO user) throws EmailAndUserNameValidationException {
        if (StringUtils.isNotBlank(user.getUser().getEmail())) {
            User newUser = userRepository.findOneByEmailAndUserName(user.getUser().getEmail(), user.getUser().getUserName());
            if (newUser != null) {
                if (newUser.getEmail().equals(user.getUser().getEmail()) || newUser.getUserName().equals(user.getUser().getUserName()))
                    throw new EmailAndUserNameValidationException();
            }
            User userDetails = new User();
            userDetails.setEmail(user.getUser().getEmail());
            userDetails.setCountry(user.getUser().getCountry());
            userDetails.setPassword(user.getUser().getPassword());
            userDetails.setUserName(user.getUser().getUserName());
            Account account = new Account();
            account.setBalance(user.getBalance());
            account.setBankName(user.getBankName());
            userDetails.setAccount(account);
            account.setUser(userDetails);
            userRepository.save(userDetails);
        }
        return new ResponseEntity("user Register SuccessFully",HttpStatus.OK);
    }


    public ResponseEntity loginUser(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user==null)
            throw new UserNotFoundException();
        else if (user.getEmail().equals(email) && password.equals(user.getPassword())) {
            user.setLoginStatus(LoginStatus.Success);
            userRepository.save(user);
            return new ResponseEntity("User Successfully Login", HttpStatus.OK);
        }
        else {
            user.setLoginStatus(LoginStatus.Fail);
            throw new RuntimeException();
        }
    }


    public ResponseEntity getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<Map<String, Object>> userAccountInfo = new ArrayList<>();
        for(User user:userList)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("userName",user.getUserName());
            data.put("userEmail",user.getEmail());
            accountRepository.findByUser(user).getUserId();
            userAccountInfo.add(data);
        }
        return new  ResponseEntity(userAccountInfo,HttpStatus.OK);
    }

}


