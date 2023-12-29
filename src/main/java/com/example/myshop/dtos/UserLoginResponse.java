package com.example.myshop.dtos;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String token;
    private String refreshToken;
}
