package com.jpa.RegisterandLogin.DTO;

import com.jpa.RegisterandLogin.entities.Benificiary;
import com.jpa.RegisterandLogin.entities.User;
import lombok.Data;

import java.util.Set;

@Data
public class BenificaryDTO {
    private Long accountNo;
    private Set<BenificaryListDTO> beneficiaryAccounts;

}
