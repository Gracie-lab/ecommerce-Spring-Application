package com.ecommerce.ecommerce.models.user;

public class UserDtoToUserMapper {
    public static User convertDtoTOUser(UserRegistrationDTO userRegistrationDto){
        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setPassword(userRegistrationDto.getPassword());;
        user.setRole(Role.CUSTOMER);

        return user;


    }
}
