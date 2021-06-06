package com.jpa.RegisterandLogin.controller;

import com.jpa.RegisterandLogin.DTO.BenificaryDTO;
import com.jpa.RegisterandLogin.entities.User;
import com.jpa.RegisterandLogin.service.BenificiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.RegisterandLogin.entities.Benificiary;
import com.jpa.RegisterandLogin.service.BenificiaryService;

import java.io.Serializable;

@RestController
@RequestMapping("/benificiary")
public class BenificiaryController  {

	@Autowired
	 private BenificiaryService benificiaryService;
	@Autowired
	private BenificiaryRepository benificiaryRepository;

	
	@PostMapping("/add")
	ResponseEntity addBenificary(@RequestBody BenificaryDTO benificiary) {
		return benificiaryService.addBenificary(benificiary);
		
	}

	@GetMapping("/get/{benificaryaccountno}/{accountno}")
	ResponseEntity getBenificary(@PathVariable(required = true,value = "benificaryaccountno")Long benificaryAcc,
								 @PathVariable(required = true,value = "accountno")Long accountNo){
		return new ResponseEntity(benificiaryService.getBenificary(benificaryAcc,accountNo),HttpStatus.OK);
	}



}
