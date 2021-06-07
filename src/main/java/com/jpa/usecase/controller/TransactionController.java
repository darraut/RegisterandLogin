package com.jpa.usecase.controller;

import com.jpa.usecase.dto.FundTransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.usecase.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;



	
	@PostMapping("/fundtransfer")
	public ResponseEntity<FundTransferDto> fundTransfer(@RequestBody FundTransferDto fundTransferDTO) {
		return transactionService.fundTransfer(fundTransferDTO);
		
	}

	@GetMapping("/get/{accountNo}/{beneficaryAccount}")
	public ResponseEntity getTransaction(@PathVariable(name = "accountNo", required = true) Long accountNo,
										 @PathVariable(name = "beneficaryAccount", required = true) Long beneficaryAccount ){
		return transactionService.getTransaction(accountNo,beneficaryAccount);

	}

	@GetMapping("/all/pagination/sort")
	public ResponseEntity getAllTransaction(@RequestParam(required = false, value = "sortby") String sortBy,
											@RequestParam(required = true, value = "pagenumber") int pageNumber,
											@RequestParam(required = true, value = "size") int size,
											@RequestParam(required = true, name = "sortdirection") Sort.Direction sortDirection) {
		return new ResponseEntity(transactionService.getAllTransaction(sortBy, pageNumber, size, sortDirection), HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
