package com.ecommerce.ecommerce.models.goodsInformation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

@Document("Orders")
@AllArgsConstructor
@RequiredArgsConstructor

public class Order {
    @Id
    private int id;

    private String status;

    @DBRef
    private LinkedList<Item> items;
}
