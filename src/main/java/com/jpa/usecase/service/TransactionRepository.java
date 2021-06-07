package com.jpa.usecase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.usecase.entities.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByAccountNoAndBenificaryAccount(Long account, Long beneficaryAccount);

    Page<Transaction> findByAccountNoContainingOrBanknameContainingOrBenificaryAccountContaining(String searchStr, String searchStr1, String searchStr2, Pageable pageable);

    Transaction findByAccountNo(Long accountNo);
}
