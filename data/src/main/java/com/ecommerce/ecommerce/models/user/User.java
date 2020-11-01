package com.ecommerce.ecommerce.models.user;

import com.ecommerce.ecommerce.models.goodsInformation.Order;
import com.ecommerce.ecommerce.models.logistics.Address;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

@Document("Users")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {
   private String name;
    private String phone;
    private Address homeAddress;

    @Indexed(unique = true)
    private String emailAddress;

    private String password;

    @DBRef
    private LinkedList<Order> order;

    @DBRef
    private StoreAdmin storeAdmin;

    @Nullable
    @DBRef
    private Customer customer;


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", homeAddress=" + homeAddress +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

 public String getRole() {
 }
}
