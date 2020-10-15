package com.ecommerce.ecommerce.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BillingInformation {
    private Address deliveryAddress;
    private CardInformation cardInformation;
    private String receiverName;
    private String receiverPhoneNumber;

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public CardInformation getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CardInformation cardInformation) {
        this.cardInformation = cardInformation;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    @Override
    public String toString() {
        return "BillingInformation{" +
                "deliveryAddress=" + deliveryAddress +
                ", cardInformation=" + cardInformation +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
