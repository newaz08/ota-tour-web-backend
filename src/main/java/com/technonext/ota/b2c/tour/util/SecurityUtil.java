package com.technonext.ota.b2c.tour.util;

import com.technonext.ota.b2c.tour.model.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {
    public static Optional<User> getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }

        return Optional.ofNullable(authentication.getPrincipal())
                .filter(User.class::isInstance)
                .map(User.class::cast);
    }
}
