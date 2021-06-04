package com.jpa.RegisterandLogin.service;

import java.util.Optional;

import com.jpa.RegisterandLogin.entities.Benificiary;
import com.jpa.RegisterandLogin.entities.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jpa.RegisterandLogin.entities.Account;
import com.jpa.RegisterandLogin.entities.User;
import com.jpa.RegisterandLogin.exception.UserNotFoundException;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userrepository;

    @Autowired
    BenificiaryRepository benificiaryRepository;


    public Account updateBalance(Double minusBalance, Long accountNo) {
        Optional<Account> account = accountRepository.findById(accountNo);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setBalance(minusBalance);
            return accountRepository.saveAndFlush(account1);
        }
        return null;
    }
}
