package com.ecommerce.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Address {
    private String cityName;
    private String streetName;
    private String houseNumber;
}
