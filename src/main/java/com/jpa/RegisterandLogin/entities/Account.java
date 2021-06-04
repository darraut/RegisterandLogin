package com.jpa.RegisterandLogin.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNo;

    @Column
    private String bankName;

    @Column
    private double balance;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "accountNo")
    //@JoinColumn(name = "benificary_acc", referencedColumnName ="accountNo" )
    private Set<Benificiary> benificiryAccountList;

    public Account(Long accountNo, String bankName, double balance, User user) {
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.balance = balance;
        this.user = user;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo=" + accountNo +
                ", bankName='" + bankName + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                ", benificiryAccountList=" + benificiryAccountList +
                '}';
    }
}
