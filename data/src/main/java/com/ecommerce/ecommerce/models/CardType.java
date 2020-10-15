package com.ecommerce.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@RequiredArgsConstructor
public enum CardType {
        MASTER_CARD, VERVE, VISA_CARD, AMERICAN_EXPRESS;
}
