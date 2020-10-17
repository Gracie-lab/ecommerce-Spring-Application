package com.ecommerce.ecommerce.models.user;

import com.ecommerce.ecommerce.models.logistics.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public abstract class User {
    private String name;
    private String phone;
    private Address homeAddress;
    private String emailAddress;
    private String password;


    abstract void jump();

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", homeAddress=" + homeAddress +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
