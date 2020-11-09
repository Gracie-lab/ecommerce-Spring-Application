package com.ecommerce.utils;


//import com.desirehealth.user.User;
//import io.jsonwebtoken.Claims;
import com.ecommerce.ecommerce.models.user.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;

public interface TokenProviderUtil {



    String getEmailFromToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    String generateRefreshToken(String email, Set<SimpleGrantedAuthority> authorities);

    String generateToken(Authentication authentication);

    String generateLoginToken(Authentication authentication, User user);

    //This generates temporary tokens for password reset
    String generatePasswordToken(String email, int validity);

    boolean isPasswordTokenValid(String token);


    boolean validateToken(String token, UserDetails userDetails);

    UsernamePasswordAuthenticationToken getAuthentication(String token, Authentication existingAuth, UserDetails userDetails);


    void invalidatePasswordToken(String token);

    String findValidPasswordTokenByEmail(String email);

    String findValidPasswordToken(String token);

    Set<String> getValidPasswordTokens();

    void setValidPasswordTokens(ConcurrentSkipListSet<String> validPasswordTokens);
}
