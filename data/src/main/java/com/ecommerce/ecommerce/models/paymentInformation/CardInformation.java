package com.ecommerce.ecommerce.models.paymentInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.YearMonth;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CardInformation {
    private short cvv = 758;
    private String cardNumber;
    private YearMonth cardExpiryDate;
    private String nameOnCard;
    private CardType cardType;




    @Override
    public String toString() {
        return "CardInformation{" +
                "cvv=" + cvv +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiryDate=" + cardExpiryDate +
                ", nameOnCard='" + nameOnCard + '\'' +
                ", cardType=" + cardType +
                '}';
    }
}
