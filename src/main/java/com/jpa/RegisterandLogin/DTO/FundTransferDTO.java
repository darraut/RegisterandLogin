package com.jpa.RegisterandLogin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferDTO {

    private Long accountNo;
    private Long benificaryAccount;
    private String bankName;
    private Double transferAmount;
}
