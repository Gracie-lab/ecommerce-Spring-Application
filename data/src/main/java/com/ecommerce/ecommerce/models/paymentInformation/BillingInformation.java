package com.ecommerce.ecommerce.models.paymentInformation;


import com.ecommerce.ecommerce.models.logistics.Address;
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
