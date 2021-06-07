package com.jpa.usecase.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BenificaryListDTO {
    private String bankName;

    @JsonIgnore
    private Double creditAmount;

    private Double balance;

    private Long benificaryAccount;

}
