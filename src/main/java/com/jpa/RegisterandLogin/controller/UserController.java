package com.jpa.RegisterandLogin.controller;


import java.util.List;

import javax.validation.Valid;

import com.jpa.RegisterandLogin.DTO.UserDTO;
import com.jpa.RegisterandLogin.entities.Account;
import com.jpa.RegisterandLogin.service.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.RegisterandLogin.entities.User;
import com.jpa.RegisterandLogin.exception.EmailAndUserNameValidationException;
import com.jpa.RegisterandLogin.service.UserService;



@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;



	@PostMapping("/signUp")
	public ResponseEntity<User> signUpNewUser(@Valid @RequestBody UserDTO user) {
		return userService.signUpNewUser(user);
	}

	@GetMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam(required = true, value = "email") String email,
										  @RequestParam(required = true, value = "password")String password ){
	 	User user= userService.loginUser(email, password);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@GetMapping("/get/{accountNo}")
	public ResponseEntity getAccount(@PathVariable(name = "accountNo", required = true) Long accountNo){
		return userService.getAccount(accountNo);
	}

	@DeleteMapping("/delete/{accountNo}")
	public ResponseEntity deleteAccount(@PathVariable(name = "accountNo", required = true) Long accountNo) {
		return userService.deleteAccount(accountNo);
	}
}

