package com.ecommerce.ecommerce.models.user;

import com.ecommerce.ecommerce.models.goodsInformation.ShoppingCart;
import com.ecommerce.ecommerce.models.paymentInformation.BillingInformation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Customers")
@AllArgsConstructor
@RequiredArgsConstructor

public class Customer implements UserInterface{
    private BillingInformation billingInformation;
    private ShoppingCart cart;

    public void register(){

    }
}
