package com.jpa.usecase.service;

import com.jpa.usecase.DTO.BenificaryDTO;
import com.jpa.usecase.DTO.BenificaryListDTO;
import com.jpa.usecase.entities.Account;
import com.jpa.usecase.exception.AccountNotFoundException;
import com.jpa.usecase.exception.BenificaryAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jpa.usecase.entities.Benificiary;

import java.util.*;

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


    public Map<String, Object> getBenificary(Long benificaryAcc, Long accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo);
        Benificiary benificaryAccount = benificiaryRepository.findByAccountNoAndBenificaryAccount(account, benificaryAcc);
        Map<String, Object> benificaryDetails = new HashMap<>();
        if (benificaryAccount == null)
            throw new BenificaryAccountException();

        benificaryDetails.put("benificaryAccountNo", benificaryAccount.getBenificaryAccount());
        benificaryDetails.put("Balance", benificaryAccount.getBalance());
        benificaryDetails.put("BankName", benificaryAccount.getBankname());
        benificaryDetails.put("UserAccountNo", benificaryAccount.getAccountNo().getAccountNo());
        return benificaryDetails;
    }
}
