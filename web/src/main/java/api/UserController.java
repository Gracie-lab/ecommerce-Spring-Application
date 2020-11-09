package api;

import com.ecommerce.ecommerce.models.user.UserRegistrationDTO;
import com.ecommerce.exception.AppException;
import com.ecommerce.service.validation.validation.UserService;
import dtoz.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/n registerUser")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        try{
            userService.registerUser(userRegistrationDTO);
        }
        catch(AppException appException){
            return ResponseEntity.badRequest().body(appException.getMessage());
        }
        return new ResponseEntity<>(new ApiResponse(true, "Registration successful"), HttpStatus.CREATED);
    }
}
