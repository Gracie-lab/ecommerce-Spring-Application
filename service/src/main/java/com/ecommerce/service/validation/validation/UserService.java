package com.ecommerce.service.validation.validation;

import com.ecommerce.ecommerce.models.user.User;
import com.ecommerce.ecommerce.models.user.UserRegistrationDTO;

import java.util.Optional;

public interface UserService {
//    void changeRole();

//
//    void checkOut();
//    void deleteItems();
//    void searchItemByName();

    User registerUser(UserRegistrationDTO userRegistrationDTO);
User saveUser(User user);
    Optional<User> findUserByEmail(String username);
}
