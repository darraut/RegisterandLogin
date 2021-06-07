package com.jpa.usecase.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class BenificaryDTO {
    private Long accountNo;
    private Set<BenificaryListDTO> beneficiaryAccounts;

}
