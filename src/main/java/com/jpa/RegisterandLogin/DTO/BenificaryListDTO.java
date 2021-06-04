package com.jpa.RegisterandLogin.DTO;

import lombok.Data;

import javax.persistence.Column;
@Data
public class BenificaryListDTO {
    private String bankName;

    private Double creditAmount;

    private Double balance;

    private Long benificaryAccount;

}
