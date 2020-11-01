package com.ecommerce.ecommerce.models.user;

import com.ecommerce.ecommerce.models.user.UserInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StoreAdmin implements UserInterface {

   public void register(){

   }
   public void addProduct(){}
}
