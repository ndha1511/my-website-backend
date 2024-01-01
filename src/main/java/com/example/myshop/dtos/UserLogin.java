package com.example.myshop.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLogin {
    private String email;
    private String password;
}
