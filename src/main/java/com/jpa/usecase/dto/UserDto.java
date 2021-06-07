package com.jpa.usecase.dto;

import com.jpa.usecase.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String bankName;
    private double balance;
    private User user;
}
