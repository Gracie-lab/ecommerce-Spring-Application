/*
 Copyright (c) 2020. Semicolon Africa
 312 Herbert Macaulay Way, Yaba, Lagos.

 Project Name: lamp
 Class Name: com.lamp.utils.TokenProvider
 File Name: TokenProvider.java
 File Path: /home/scv2003/IdeaProjects/lampOnboarding/service/src/main/java/com/lamp/utils/TokenProvider.java
 Author:  scv2003
 Last Modified: 19/04/2020, 3:22 AM.

 The contents of this file and project are not available to the public.

 */

package com.ecommerce.utils;


import com.ecommerce.exception.AppException;
import com.ecommerce.ecommerce.models.user.User;
import com.ecommerce.ecommerce.models.user.User;
import com.ecommerce.exception.AppException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@PropertySource("classpath:securityConstants.properties")
public class TokenProviderUtilImpl implements Serializable, TokenProviderUtil {



    @Value("${SIGNING_KEY}")
    private  String SIGNING_KEY;


    Logger logger = LoggerFactory.getLogger(TokenProviderUtilImpl.class);

    @Value("${AUTHORITIES_KEY}")
    private String AUTHORITIES_KEY;

    private final int AUTHORITY =5;

    private final int ACCESS_TOKEN_VALIDITY = 5* 3_600_000;//5hrs hour

    private  ConcurrentSkipListSet<String> validPasswordTokens = new ConcurrentSkipListSet<>();



    @Override
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }


    @Override
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try{
        claims =Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
            return claims;
        }
        catch (SignatureException ex){
            logger.error("untrusted token detected and invalidated");
            throw new AppException("token untrusted");

        }

    }

    private boolean isTokenExpired(String token) {
        logger.info("checking if token has expired");
        final Date expiration = getExpirationDateFromToken(token);
        logger.info("token expiration date is "+ expiration);
        return expiration.before(new Date());
    }

    @Override
    public String generateRefreshToken(String email, Set<SimpleGrantedAuthority> authorities){
       final String authority = authorities
               .stream()
               .map(role -> role.getAuthority().substring(AUTHORITY))
               .collect(Collectors.joining(","));
        //        validTokens.add(token);

        return Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, authority)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .compact();

    }

    @Override
    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .compact();

        return token;
    }




    @Override
    public String generateLoginToken(Authentication authentication, User user) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
//                .claim("userData", user)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .compact();
    }




    @Override
    public String generatePasswordToken(String email, int validity){
        String token = Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))

           //     .setExpiration(new Date(System.currentTimeMillis() + validity)) disabled because we dont want the password token to expire
                .compact();
        logger.info("Token issued at " + System.currentTimeMillis());

        validPasswordTokens.add(token);
        logger.info("password create token generated");
        return token;
    }


    @Override
    public boolean isPasswordTokenValid(String token){
        logger.info("validating token ");
        boolean isPasswordTokenValid= (validPasswordTokens.contains(token));
        logger.info("is Password token valid? "+ isPasswordTokenValid);
        return isPasswordTokenValid;
   }



    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmailFromToken(token);
        logger.info("validating token ");
//        boolean tokenStatus =  email.equals(userDetails.getUsername())  && validTokens.contains(token) && (!isTokenExpired(token));
        boolean tokenStatus =  email.equals(userDetails.getUsername()) && (!isTokenExpired(token));
        logger.info("is bearer token valid? " + tokenStatus);
        return tokenStatus;
    }

    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }


    @Override
    public void invalidatePasswordToken(String token) {
        validPasswordTokens.remove(token);
        logger.info("password token invalidated");
    }

    @Override
    public String findValidPasswordTokenByEmail(String email) {
        for (String token: validPasswordTokens){
           if (getEmailFromToken(token).equalsIgnoreCase(email)){
               return token;
           }
        }
        return null;
    }

    @Override
    public String findValidPasswordToken(String token) {
        for (String validToken: validPasswordTokens){
            if (validToken.equals(token)){
                return validToken;
            }
        }
        return null;
    }


    @Override
    public Set<String> getValidPasswordTokens() {
        return validPasswordTokens;
    }

    @Override
    public void setValidPasswordTokens(ConcurrentSkipListSet<String> validPasswordTokens) {
        this.validPasswordTokens = validPasswordTokens;
    }



}
