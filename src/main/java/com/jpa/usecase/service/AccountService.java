package com.jpa.usecase.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.usecase.entities.Account;

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
