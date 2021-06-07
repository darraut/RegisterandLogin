package com.jpa.usecase.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.usecase.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmailAndUserName(String email, String userName);

	User findByEmailAndPassword(String email, String password);

	User findByUserName(String userName);

	User findByEmail(String email);
}
