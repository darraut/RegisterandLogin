package com.jpa.usecase.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BenificaryLisDto {
    private String bankName;

    @JsonIgnore
    private Double creditAmount;

    private Double balance;

    private Long benificaryAccount;

}
