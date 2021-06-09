package com.jpa.usecase.controller;


import javax.validation.Valid;

import com.jpa.usecase.dto.UserDto;
import com.jpa.usecase.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.usecase.entities.User;
import com.jpa.usecase.service.UserService;

import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;



	@PostMapping("/signUp")
	public ResponseEntity<User> signUpNewUser(@Valid @RequestBody UserDto user) {
		User userDetails = userService.signUpNewUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDetails);
	}

	@GetMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam(required = true, value = "email") String email,
										  @RequestParam(required = true, value = "password")String password ){
	 	User user= userService.loginUser(email, password);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping("/get/{accountNo}")
	public ResponseEntity<Account> getAccount(@PathVariable(name = "accountNo", required = true) Long accountNo){
		Map<String,Object>data = userService.getAccount(accountNo);
		return new ResponseEntity(data, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{accountNo}")
	public ResponseEntity<Account> deleteAccount(@PathVariable(name = "accountNo", required = true) Long accountNo) {
		 userService.deleteAccount(accountNo);
		 return new ResponseEntity("Account deleted SuccessFully",HttpStatus.OK);

	}
}

