package com.jpa.RegisterandLogin.DTO;

import lombok.Data;

@Data
public class FundTransferDTO {

    private Long accountNo;
    private Long benificaryAccount;
    private String bankName;
    private Double transferAmount;

    public FundTransferDTO(Long accountNo, Long benificaryAccount, String bankName, Double transferAmount) {
        this.accountNo = accountNo;
        this.benificaryAccount = benificaryAccount;
        this.bankName = bankName;
        this.transferAmount = transferAmount;
    }

    public FundTransferDTO() {
    }
}
