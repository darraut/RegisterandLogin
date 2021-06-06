package com.jpa.RegisterandLogin.service;

import com.jpa.RegisterandLogin.DTO.BenificaryListDTO;
import com.jpa.RegisterandLogin.DTO.FundTransferDTO;
import com.jpa.RegisterandLogin.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.jpa.RegisterandLogin.entities.Benificiary;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BenificiaryRepository extends JpaRepository<Benificiary, Long> {


    Benificiary findByBenificaryAccount(Long benificaryAccount);

    Benificiary findByAccountNoAndBenificaryAccount(Account accountNo, Long benificaryAccount);

    Benificiary findByAccountNo(Account accountNo);
   // Benificiary findByAccountNo(Long accountNo);

}
