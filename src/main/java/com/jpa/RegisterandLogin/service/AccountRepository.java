package com.jpa.RegisterandLogin.service;

import com.jpa.RegisterandLogin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jpa.RegisterandLogin.entities.Account;

import javax.persistence.EntityManager;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNo(Long accountNo);
    User findByUser(User user);

}
