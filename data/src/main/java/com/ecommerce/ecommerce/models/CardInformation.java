package com.ecommerce.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.YearMonth;

@Document
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CardInformation {
    private short cvv = 758;
    private String cardNumber;
    private YearMonth cardExpiryDate;
    private String nameOnCard;
    private CardType cardType;

    public short getCvv() {
        return cvv;
    }

    public void setCvv(short cvv) {
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public YearMonth getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(YearMonth cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }




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
