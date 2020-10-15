package com.ecommerce.ecommerce.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ShoppingCart {
    private Item item;
    private int quantity;
}
