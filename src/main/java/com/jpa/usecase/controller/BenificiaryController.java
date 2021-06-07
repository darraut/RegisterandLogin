package com.jpa.usecase.controller;

import com.jpa.usecase.DTO.BenificaryDTO;
import com.jpa.usecase.service.BenificiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.usecase.service.BenificiaryService;

@RestController
@RequestMapping("/benificiary")
public class BenificiaryController  {

	@Autowired
	 private BenificiaryService benificiaryService;
	@Autowired
	private BenificiaryRepository benificiaryRepository;

	
	@PostMapping("/add")
	public ResponseEntity addBenificary(@RequestBody BenificaryDTO benificiary) {
		return benificiaryService.addBenificary(benificiary);
		
	}

	@GetMapping("/get/{benificaryaccountno}/{accountno}")
	public ResponseEntity getBenificary(@PathVariable(required = true,value = "benificaryaccountno")Long benificaryAcc,
								 @PathVariable(required = true,value = "accountno")Long accountNo){
		return new ResponseEntity(benificiaryService.getBenificary(benificaryAcc,accountNo),HttpStatus.OK);
	}



}
