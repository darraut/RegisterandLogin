package com.jpa.RegisterandLogin.service;

import com.jpa.RegisterandLogin.DTO.FundTransferDTO;
import com.jpa.RegisterandLogin.entities.*;
import com.jpa.RegisterandLogin.exception.AccountNotFoundException;
import com.jpa.RegisterandLogin.exception.BalanceInefficient;
import com.jpa.RegisterandLogin.exception.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class TransactionService {

	@Autowired
	BenificiaryRepository benificiaryRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TransactionRepository transactionRepository;

	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


	public ResponseEntity fundTransfer(FundTransferDTO fundTransferDTO) throws UserNotFoundException {
		Optional<User> userid = userRepository.findById(fundTransferDTO.getAccountNo()).filter(u->u.getLoginStatus().equals(LoginStatus.Success));
		if(userid.isPresent()) {
			Account accountNo = accountRepository.findByAccountNo(fundTransferDTO.getAccountNo());
			Benificiary benificiary = benificiaryRepository.findByAccountNoAndBenificaryAccount(accountNo, fundTransferDTO.getBenificaryAccount());
			if (benificiary != null) {
				double userBalance = accountRepository.findById(benificiary.getAccountNo().getAccountNo()).map(Account::getBalance).get();
				if (fundTransferDTO.getTransferAmount() <= userBalance) {
					double minusBalance = 0.00;
					minusBalance = userBalance - fundTransferDTO.getTransferAmount();
					Transaction transaction = new Transaction();
					transaction.setBalance(minusBalance);
					transaction.setAccountNo(fundTransferDTO.getAccountNo());
					transaction.setBankname(fundTransferDTO.getBankName());
					transaction.setTransferAmount(fundTransferDTO.getTransferAmount());
					transaction.setBenificaryAccount(fundTransferDTO.getBenificaryAccount());
					transactionRepository.save(transaction);
					benificiary.setBalance(benificiary.getBalance() + fundTransferDTO.getTransferAmount());
					benificiary.setAccountNo(accountRepository.findById(fundTransferDTO.getAccountNo()).get());
					benificiary.setBenificaryAccount(fundTransferDTO.getBenificaryAccount());
					benificiary.setCreditAmount(fundTransferDTO.getTransferAmount());
					benificiary.setBankname(fundTransferDTO.getBankName());
					benificiaryRepository.save(benificiary);
					accountService.updateBalance(minusBalance, fundTransferDTO.getAccountNo());
					return new ResponseEntity("Transaction done Successfully", HttpStatus.OK);
				} else
					throw new BalanceInefficient();
			}
		}
		throw new AccountNotFoundException();
	}

	public ResponseEntity getTransaction(Long accountNo, Long beneficaryAccount) {
		List<Transaction> transactionList = transactionRepository.findByAccountNoAndBenificaryAccount(accountNo, beneficaryAccount);
		List<Map<String, Object>> paymentListData = new ArrayList<>();
		if (transactionList.size() != 0) {
			for (Transaction data : transactionList) {
				Map<String, Object> map = new HashMap<>();
				map.put("account_no", data.getAccountNo());
				map.put("balance", data.getBalance());
				map.put("bankname", data.getBankname());
				map.put("benificaryAccount", data.getBenificaryAccount());
				map.put("TransferAmount", data.getTransferAmount());
				map.put("Date", formatter.format(data.getCreatedDate()));
				paymentListData.add(map);
			}
			return new ResponseEntity(paymentListData, HttpStatus.OK);
		} else
			throw new AccountNotFoundException();
	}

	public ResponseEntity getAllTransaction(String searchStr, String sortBy, int pageNumber, int size, Sort.Direction sortDirection) {
		Map<String, Object> dataTables = new HashMap<>();
		Sort sort = null;
		if (StringUtils.isBlank(sortBy)) {
			sortBy = "createdDate";   // default sort by date
			sortDirection = Sort.Direction.DESC;
		}
		if (sortDirection.isAscending()) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, size, sort);
		Page<Transaction> page = null;
		// page = transactionRepository.findByAccountNoContainingOrBanknameContainingOrBenificaryAccountContaining(searchStr, searchStr, searchStr, pageable);
		page = transactionRepository.findAll(pageable);
		List<Transaction> list = page.getContent();
		int totalPages = page.getTotalPages();
		int currentPage = pageNumber;
		long totalRecords = page.getTotalElements();
		List<Map<String, Object>> paymentListData = new ArrayList<>();
		for (Transaction transaction : list) {
			Map<String, Object> data = new HashMap<>();
			data.put("AccountNo", transaction.getAccountNo());
			data.put("BeneficaryAccount", transaction.getBenificaryAccount());
			data.put("BankName", transaction.getBankname());
			data.put("UpdatedBalance", transaction.getBalance());
			data.put("TransferAmount", transaction.getTransferAmount());
			data.put("Date", formatter.format(transaction.getCreatedDate()));
			paymentListData.add(data);
		}
		dataTables.put("paymentList", paymentListData);
		dataTables.put("totalPages", totalPages);
		dataTables.put("currentPage", currentPage);
		dataTables.put("totalRecords", totalRecords);
		return new ResponseEntity(dataTables,HttpStatus.OK);
	}

}
