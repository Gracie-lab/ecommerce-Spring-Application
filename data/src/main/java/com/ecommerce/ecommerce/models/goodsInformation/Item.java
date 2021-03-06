package com.ecommerce.ecommerce.models.goodsInformation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Items")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Item {
    private byte itemID;
    private double price;
    private String name;
    private String description;
    private String itemType;
    private String colour;
    private String seller;

}
