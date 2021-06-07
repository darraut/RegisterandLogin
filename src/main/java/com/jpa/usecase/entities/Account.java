package com.jpa.usecase.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNo;

    @Column
    @NotNull
    private String bankName;

    @Column
    @NotNull(message = "Should be in digit format")
    private double balance;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "accountNo")
    private Set<Benificiary> benificiryAccountList;

    public Account(Long accountNo, String bankName, double balance, User user) {
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.balance = balance;
        this.user = user;
    }

    public Account() {
    }

}
