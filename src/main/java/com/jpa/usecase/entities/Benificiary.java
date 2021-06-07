package com.jpa.usecase.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_beneficary")
public class Benificiary  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long benificiaryId;

	@ManyToOne
	@JoinColumn(name = "account_no",nullable = false)
	private Account accountNo;

	@Column
	private String bankname;

	@Column
	private Double creditAmount;


	@Column
	private Double balance;

	@Column
	private Long benificaryAccount;

	
}
