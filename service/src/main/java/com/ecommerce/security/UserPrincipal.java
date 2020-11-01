/*
 Copyright (c) 2020. Semicolon Africa
 312 Herbert Macaulay Way, Yaba, Lagos.

 Project Name: lamp
 Class Name: com.lamp.models.UserPrincipal
 File Name: UserPrincipal.java
 File Path: /home/scv2003/IdeaProjects/lampOnboarding/models/src/main/java/com/lamp/models/UserPrincipal.java
 Author:  scv2003
 Last Modified: 30/04/2020, 11:27 PM.

 The contents of this file and project are not available to the public.
 */
/*
 *
 *
 *
 * */
package com.ecommerce.security;

import com.desirehealth.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private  UUID id;

    private  String name;


    @JsonIgnore
    private  String email;

    //email == username
    @JsonIgnore
    private  String username = email;

    @JsonIgnore
    private  String password;

    private Collection<? extends GrantedAuthority> authorities;
//
//    public static UserPrincipal create(User user) {
//        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
//                new SimpleGrantedAuthority(role.getRole())
//        ).collect(Collectors.toList());
//
//        return new UserPrincipal(
//                user.getId(),
//                user.getFirstName() + " " + user.getLastName(),
//                user.getEmail(), //email is userName, replace this with username if that changes
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new UserPrincipal(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(), //email is userName, replace this with username if that changes
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }


    @Override
    public String getUsername() {

        return username;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    //Overriden to avoid credentials been posted on the spring  JWT Authentication Filter log
    //We only want the first name and last name on our logs
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserPrincipal{");
        sb.append(", name='").append(name).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append('}');
        return sb.toString();
    }
}