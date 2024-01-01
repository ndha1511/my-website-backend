package com.example.myshop.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String verificationCode;
    private LocalDateTime verificationDate;
}
