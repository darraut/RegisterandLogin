package com.jpa.usecase.DTO;

import com.jpa.usecase.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String bankName;
    private double balance;
    private User user;
}
