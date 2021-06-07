package com.jpa.usecase.service;


import java.util.*;

import com.jpa.usecase.DTO.UserDTO;
import com.jpa.usecase.entities.Account;
import com.jpa.usecase.entities.LoginStatus;
import com.jpa.usecase.exception.AccountNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.jpa.usecase.entities.User;
import com.jpa.usecase.exception.EmailAndUserNameValidationException;
import com.jpa.usecase.exception.UserNotFoundException;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BenificiaryRepository benificiaryRepository;

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
            return new ResponseEntity(user.getUser().getUserName()+ " "+"Register SuccessFully",HttpStatus.OK);
        }
        throw new RuntimeException();
    }


    public User loginUser(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user==null)
            throw new UserNotFoundException();
        else if (user.getEmail().equals(email) && password.equals(user.getPassword())) {
            user.setLoginStatus(LoginStatus.Success);
            userRepository.save(user);
            return user;
        }
        else {
            user.setLoginStatus(LoginStatus.Fail);
            throw new RuntimeException();
        }
    }


    public ResponseEntity getAccount(Long accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (account == null)
            throw new AccountNotFoundException();
            Map<String,Object>map=new HashMap<>();
            map.put("accountNo",account.getAccountNo());
            map.put("Balance",account.getBalance());
            map.put("BankName",account.getBankName());
            map.put("UserName",userRepository.findByUserName(account.getUser().getUserName()).getUserName());
            map.put("mail",userRepository.findByEmail(account.getUser().getEmail()).getEmail());
            return new ResponseEntity(map,HttpStatus.OK);
    }

    public ResponseEntity deleteAccount(Long accountNo) {
        accountRepository.deleteById(accountNo);
        return new ResponseEntity("Account Delete Successfully",HttpStatus.OK);
    }
}


