package com.jpa.usecase.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BenificaryDto {
    private Long accountNo;
    private Set<BenificaryLisDto> beneficiaryAccounts;

}
