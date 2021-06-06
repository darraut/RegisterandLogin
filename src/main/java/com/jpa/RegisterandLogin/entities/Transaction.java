package com.jpa.RegisterandLogin.entities;

import com.jpa.RegisterandLogin.Utility.BaseEntitySeq;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_transaction")
public class Transaction extends BaseEntitySeq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column
    @NotNull
    private Long accountNo;

    @Column
    private String bankname;

    @Column
    private Double balance;

    @Column
    @NotNull
    private Long benificaryAccount;

    @Column
    @NotNull
    private Double transferAmount;


    public Transaction() {

    }

	public Transaction(Long transactionId, Long accountNo, String bankname, Double balance, Long benificaryAccount, Double transferAmount) {
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.bankname = bankname;
		this.balance = balance;
		this.benificaryAccount = benificaryAccount;
		this.transferAmount = transferAmount;
	}
}
