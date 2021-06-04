package com.jpa.RegisterandLogin.entities;

import com.jpa.RegisterandLogin.Utility.BaseEntitySeq;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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

	private Double creditAmount;


	@Column
	private Double balance;

	@Column
	private Long benificaryAccount;

	
}
