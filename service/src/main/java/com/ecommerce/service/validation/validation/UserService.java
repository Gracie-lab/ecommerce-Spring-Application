package com.ecommerce.service.validation.validation;

import com.ecommerce.ecommerce.models.user.User;

import java.util.Optional;

public interface UserService {
//    void changeRole();


    void checkOut();
    void deleteItems();
    void searchItemByName();

    Optional<User> findUserByEmail(String username);
}
