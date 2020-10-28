package com.ecommerce.ecommerce.models.goodsInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

@Document
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Inventory {

    @DBRef
    private LinkedList<Item> items;
}
