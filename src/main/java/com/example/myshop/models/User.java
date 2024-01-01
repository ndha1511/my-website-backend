package com.example.myshop.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private List<Address> addresses;
    private LocalDate dob;
    private boolean gender;
    private String avatar;
    private String role;
    private boolean enable;
    private String verificationCode;
    private LocalDateTime verificationDate;

}
