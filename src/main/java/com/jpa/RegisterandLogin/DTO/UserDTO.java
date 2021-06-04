package com.jpa.RegisterandLogin.DTO;

import com.jpa.RegisterandLogin.entities.User;
import lombok.Data;


@Data
public class UserDTO {
    private String bankName;
    private double balance;
    private User user;
}
