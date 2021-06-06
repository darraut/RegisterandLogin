package com.jpa.RegisterandLogin.service;

import com.jpa.RegisterandLogin.DTO.BenificaryDTO;
import com.jpa.RegisterandLogin.DTO.BenificaryListDTO;
import com.jpa.RegisterandLogin.entities.Account;
import com.jpa.RegisterandLogin.exception.AccountNotFoundException;
import com.jpa.RegisterandLogin.exception.BenificaryAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jpa.RegisterandLogin.entities.Benificiary;

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
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> benificaryDetails = new HashMap<>();
        if (benificaryAccount == null)
            throw new BenificaryAccountException();

        benificaryDetails.put("benificaryAccountNo", benificaryAccount.getBenificaryAccount());
        benificaryDetails.put("Balance", benificaryAccount.getBalance());
        benificaryDetails.put("BankName", benificaryAccount.getBankname());
        benificaryDetails.put("UserAccountNo", benificaryAccount.getAccountNo());
        return benificaryDetails;
    }
}
