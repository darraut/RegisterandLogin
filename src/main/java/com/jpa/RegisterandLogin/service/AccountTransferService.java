package com.jpa.RegisterandLogin.service;

import com.jpa.RegisterandLogin.DTO.FundTransferDTO;
import com.jpa.RegisterandLogin.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jpa.RegisterandLogin.exception.UserNotFoundException;

import java.util.Optional;

@Service
public class AccountTransferService {

	@Autowired 
	AccountTransferRepository accounttransferrepo;
	
	@Autowired
	BenificiaryRepository benificiryrepo;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;
	@Autowired
	UserRepository userRepository;
	

	public AccountTransfer addAccountInfo(AccountTransfer accounttransfer) {
		return accounttransferrepo.save(accounttransfer);
	}

	public ResponseEntity fundTransfer(FundTransferDTO fundTransferDTO) throws UserNotFoundException {
		/*AccountTransfer result = accounttransferrepo.findByAccountNoAndUserId(fundTransferDTO.getAccountNo(), fundTransferDTO.getUserId());
		if (result != null) {
			if (result.getBalance() != null) {
				Benificiary benificiary = benificiryrepo.findByAccountNoAndUserId(fundTransferDTO.getToAccount(),fundTransferDTO.getUserId());
				if (benificiary.getAccountNo().equals(fundTransferDTO.getToAccount())) {*/
				 Optional<User> userid = userRepository.findById(fundTransferDTO.getUserId()).filter(u->u.getLoginStatus().equals(LoginStatus.Success));
				if(userid.isPresent()) {
					Benificiary benificiary1 = new Benificiary();
					AccountTransfer accountTransfer = new AccountTransfer();
					Double minusBalnce = null;
					Long user = fundTransferDTO.getUserId();
					Double balance = accountRepository.findByUserId(user).getBalance();
					minusBalnce = balance - fundTransferDTO.getTransferAmount();
					accountTransfer.setBalance(minusBalnce);
					accountTransfer.setAccountNo(fundTransferDTO.getAccountNo());
					accountTransfer.setBankname(accountRepository.findByUserId(user).getIfcsCode());
					accountTransfer.setTransferamount(fundTransferDTO.getTransferAmount());
					accountTransfer.setToaccount(fundTransferDTO.getToAccount());
					accountTransfer.setUserId(fundTransferDTO.getUserId());
					accounttransferrepo.save(accountTransfer);
					benificiary1.setBalance((accountRepository.findByUserId(user).getBalance() + fundTransferDTO.getTransferAmount()));
					benificiary1.setAccountNo(fundTransferDTO.getToAccount());
					benificiary1.setCreditAmount(fundTransferDTO.getTransferAmount());
					benificiary1.setBankname(accountRepository.findByUserId(user).getIfcsCode());
					benificiary1.setUserId(fundTransferDTO.getUserId());
					benificiryrepo.save(benificiary1);
					accountService.updateBalance(minusBalnce, fundTransferDTO.getAccountNo());
					return new ResponseEntity("Beneficary Information is save", HttpStatus.OK);
				}
				throw new UserNotFoundException();
	}
}
