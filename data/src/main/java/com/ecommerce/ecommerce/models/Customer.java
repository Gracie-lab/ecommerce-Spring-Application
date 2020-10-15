package com.ecommerce.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Customers")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer extends User {
    private BillingInformation billingInformation;
    private ShoppingCart cart;

    @Override
    void jump() {

    }
}
