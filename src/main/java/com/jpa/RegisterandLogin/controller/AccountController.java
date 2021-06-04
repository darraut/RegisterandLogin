package com.jpa.RegisterandLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.RegisterandLogin.entities.Account;
import com.jpa.RegisterandLogin.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;



	
	
	
	
}
