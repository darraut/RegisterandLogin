package com.jpa.RegisterandLogin.controller;

import com.jpa.RegisterandLogin.DTO.FundTransferDTO;
import com.jpa.RegisterandLogin.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.RegisterandLogin.entities.Transaction;
import com.jpa.RegisterandLogin.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;



	
	@PostMapping("/fundtransfer")
	public ResponseEntity fundTransfer(@RequestBody FundTransferDTO fundTransferDTO) {
		return transactionService.fundTransfer(fundTransferDTO);
		
	}

	@GetMapping("/get/{accountNo}/{beneficaryAccount}")
	public ResponseEntity getTransaction(@PathVariable(name = "accountNo", required = true) Long accountNo,
										 @PathVariable(name = "beneficaryAccount", required = true) Long beneficaryAccount ){
		return transactionService.getTransaction(accountNo,beneficaryAccount);

	}

	@GetMapping("/all/pagination/sort")
	public ResponseEntity getAllTransaction(@RequestParam(required = true, value = "searchstr") String searchStr,
											@RequestParam(required = false, value = "sortby") String sortBy,
											@RequestParam(required = true, value = "pagenumber") int pageNumber,
											@RequestParam(required = true, value = "size") int size,
											@RequestParam(required = true, name = "sortdirection") Sort.Direction sortDirection) {
		return new ResponseEntity(transactionService.getAllTransaction(searchStr, sortBy, pageNumber, size, sortDirection), HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
