package com.jpa.RegisterandLogin.entities;

import com.jpa.RegisterandLogin.Utility.BaseEntitySeq;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_transaction")
public class Transaction extends BaseEntitySeq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;

    @Column
    private Long accountNo;

    @Column
    private String bankname;

    @Column
    private Double balance;

    @Column
    private Long benificaryAccount;

    @Column
    private Double transferAmount;


    public Transaction() {

    }

	public Transaction(Long accountID, Long accountNo, String bankname, Double balance, Long benificaryAccount, Double transferAmount) {
		this.accountID = accountID;
		this.accountNo = accountNo;
		this.bankname = bankname;
		this.balance = balance;
		this.benificaryAccount = benificaryAccount;
		this.transferAmount = transferAmount;
	}
}
