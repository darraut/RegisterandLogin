package com.jpa.RegisterandLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.RegisterandLogin.entities.Benificiary;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class BenificiaryService {

@Autowired
BenificiaryRepository benificiaryRepository;

public Benificiary saveBenificiary(Benificiary benificiary) {
	return benificiaryRepository.save(benificiary);
}
}
