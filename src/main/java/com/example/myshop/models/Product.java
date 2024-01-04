package com.example.myshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String productName;
    private List<Image> images;
}
