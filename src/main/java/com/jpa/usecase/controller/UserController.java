package com.jpa.usecase.controller;


import javax.validation.Valid;

import com.jpa.usecase.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.usecase.entities.User;
import com.jpa.usecase.service.UserService;



@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;



	@PostMapping("/signUp")
	public ResponseEntity<User> signUpNewUser(@Valid @RequestBody UserDto user) {
		return userService.signUpNewUser(user);
	}

	@GetMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam(required = true, value = "email") String email,
										  @RequestParam(required = true, value = "password")String password ){
	 	User user= userService.loginUser(email, password);
		return new ResponseEntity(user.getUserName()+ " " +"is Login SuccessFully", HttpStatus.OK);
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

