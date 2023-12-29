package com.example.myshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Address {
    private String street;
    private String state;
    private String city;
    private String zipCode;
}
