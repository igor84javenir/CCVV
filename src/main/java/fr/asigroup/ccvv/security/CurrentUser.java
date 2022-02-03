package fr.asigroup.ccvv.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public class CurrentUser {

    private static UserDetails connectedUser;

    public static UserDetails getCurrentUserDetails() {
        if (connectedUser == null || !connectedUser.equals((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            connectedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return connectedUser;
        } else {
            return connectedUser;
        }
    }


}
