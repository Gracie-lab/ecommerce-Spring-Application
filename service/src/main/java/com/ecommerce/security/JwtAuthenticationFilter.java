package com.ecommerce.security;

import com.ecommerce.utils.TokenProviderUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserPrincipalService userPrincipalService;

    @Autowired
    private TokenProviderUtil jwtTokenUtil;

 //   @Value("${HEADER_STRING}")
    private String HEADER_STRING ="Authorization";
//    @Value("${TOKEN_PREFIX}")
    private String TOKEN_PREFIX ="Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX+" ")) {
            int TOKEN_PREFIX_LENGTH = 7;
            authToken = header.substring(TOKEN_PREFIX_LENGTH);
            try {
                username = jwtTokenUtil.getEmailFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userPrincipalService.loadUserByEmail(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context" + authentication.toString() + " " + authentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        chain.doFilter(req, res);
    }
}