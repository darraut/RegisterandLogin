package com.jpa.RegisterandLogin.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.RegisterandLogin.DTO.BenificaryDTO;
import com.jpa.RegisterandLogin.DTO.BenificaryListDTO;
import com.jpa.RegisterandLogin.entities.Account;
import com.jpa.RegisterandLogin.entities.Transaction;
import com.jpa.RegisterandLogin.entities.User;
import com.jpa.RegisterandLogin.exception.AccountNotFoundException;
import com.jpa.RegisterandLogin.exception.BenificaryAccountException;
import com.jpa.RegisterandLogin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import com.jpa.RegisterandLogin.entities.Benificiary;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BenificiaryService {

    @Autowired
    BenificiaryRepository benificiaryRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity addBenificary(BenificaryDTO benificaryDTO)  {
        Long benificaryAcc=null;
        for (BenificaryListDTO e: benificaryDTO.getBeneficiaryAccounts()){
            benificaryAcc = e.getBenificaryAccount();
        }
        Account accountNo = accountRepository.findByAccountNo(benificaryDTO.getAccountNo());
        Benificiary benificiaryAccount = benificiaryRepository.findByAccountNoAndBenificaryAccount(accountNo,benificaryAcc);
        if(benificiaryAccount==null) {
            Optional<Account> account1 = Optional.ofNullable(accountRepository.findById(benificaryDTO.getAccountNo()).orElseThrow(AccountNotFoundException::new));
            if (account1.isPresent()) {
                benificaryDTO.getBeneficiaryAccounts().stream().forEach(data -> {
                    Benificiary benificiary = new Benificiary();
                    benificiary.setAccountNo(account1.get());
                    benificiary.setBalance(data.getBalance());
                    benificiary.setBankname(data.getBankName());
                    benificiary.setCreditAmount(data.getCreditAmount());
                    benificiary.setBenificaryAccount(data.getBenificaryAccount());
                    benificiaryRepository.save(benificiary);
                });
                return new ResponseEntity("Benificary account Added successfully", HttpStatus.OK);
            }

        }
        throw new BenificaryAccountException();
    }


    public ResponseEntity getBenificary(Long benificaryAcc,Long accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo);
        Benificiary benificaryAccount = benificiaryRepository.findByAccountNoAndBenificaryAccount(account,benificaryAcc);
        if (benificaryAccount != null) {
            Map<String, Object> benificaryDetails = new HashMap<>();
            benificaryDetails.put("benificaryAccountNo", benificaryAccount.getBenificaryAccount());
            benificaryDetails.put("Balance", benificaryAccount.getBalance());
            benificaryDetails.put("BankName", benificaryAccount.getBankname());
            benificaryDetails.put("UserAccountNo", benificaryAccount.getAccountNo());
            return new ResponseEntity(benificaryDetails,HttpStatus.OK);
        } else
            throw new AccountNotFoundException();
    }
}
