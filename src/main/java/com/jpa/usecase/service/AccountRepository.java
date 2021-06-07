package com.jpa.usecase.service;

import com.jpa.usecase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.usecase.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNo(Long accountNo);
    User findByUser(User user);

}
