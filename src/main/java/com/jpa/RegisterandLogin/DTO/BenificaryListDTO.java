package com.jpa.RegisterandLogin.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
@Data
public class BenificaryListDTO {
    private String bankName;

    @JsonIgnore
    private Double creditAmount;

    private Double balance;

    private Long benificaryAccount;

}
