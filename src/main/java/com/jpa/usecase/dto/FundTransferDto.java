package com.jpa.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferDto {

    private Long accountNo;
    private Long benificaryAccount;
    private String bankName;
    private Double transferAmount;
}
