package com.jpa.usecase.service;

import com.jpa.usecase.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.usecase.entities.Benificiary;

@Repository
public interface BenificiaryRepository extends JpaRepository<Benificiary, Long> {


    Benificiary findByBenificaryAccount(Long benificaryAccount);

    Benificiary findByAccountNoAndBenificaryAccount(Account accountNo, Long benificaryAccount);

    Benificiary findByAccountNo(Account accountNo);
   // Benificiary findByAccountNo(Long accountNo);

}
