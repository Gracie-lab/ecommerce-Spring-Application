package com.ecommerce.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Orders")
@AllArgsConstructor
@RequiredArgsConstructor

public class Order {
}
