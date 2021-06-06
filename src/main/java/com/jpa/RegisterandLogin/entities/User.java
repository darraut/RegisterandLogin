package com.jpa.RegisterandLogin.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import com.sun.istack.NotNull;
@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String password;

    @Column
    @Email(message = "Email Should be in a Email format")
    @NotNull
    private String email;

    @Column
    private String country;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
    Account account;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", password="
                + password + ", email=" + email + ", country="
                + country + "]";
    }

    public User(Long userId, String userName, String password, @Email(message = "Email Should be in a Email format") String email, String country, LoginStatus loginStatus) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.country = country;
        this.loginStatus = loginStatus;
    }

    public User() {
    }

    public User(@Email(message = "Email Should be in a Email format") String email,String password) {
        this.email = email;
        this.password = password;
    }

}
