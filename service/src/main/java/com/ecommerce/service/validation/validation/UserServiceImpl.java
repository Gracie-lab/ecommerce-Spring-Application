package com.ecommerce.service.validation.validation;

import com.ecommerce.ecommerce.models.user.*;
import com.ecommerce.exception.AppException;
import com.ecommerce.security.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncode;
    private UserPrincipalService userPrincipalService;


    @Override
    public User registerUser(UserRegistrationDTO userRegistrationDTO) throws AppException {
        Optional<User> optionalUser = findUserByEmail(userRegistrationDTO.getEmail());
        if(optionalUser.isPresent()){
            throw new AppException("User is already registered");
        }
        else{
            User newUser = UserDtoToUserMapper.convertDtoTOUser(userRegistrationDTO);
            String encodedPassword = new BCryptPasswordEncoder().encode(userRegistrationDTO.getPassword());
            newUser.setRole(Role.CUSTOMER);
            return saveUser(newUser);
        }

    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String username) {
        return Optional.empty();
    }
}
