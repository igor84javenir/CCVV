package fr.asigroup.ccvv.security;

import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.service.UserNotFoundException;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrentUser {

    private static UserService userService;

    @Autowired
    private CurrentUser(UserService userService) {
        CurrentUser.userService = userService;
    }

    private static UserDetails connectedUser;

    public static UserDetails getCurrentUserDetails() {
        if (connectedUser == null || !connectedUser.equals((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            connectedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return connectedUser;
        } else {
            return connectedUser;
        }
    }


    public static boolean checkAccessRights(Rdv rdv) throws UserNotFoundException {
        for (GrantedAuthority grantedAuthority : getCurrentUserDetails().getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(User.UserRole.ROLE_ADMIN.toString()) || grantedAuthority.getAuthority().equals(User.UserRole.ROLE_SUPERADMIN.toString())) {
                return true;
            }
        }

        User currentConnectedUser = userService.getUserByName(getCurrentUserDetails().getUsername());

        if (currentConnectedUser.getCity().getName().equals(rdv.getCity().getName())
                || currentConnectedUser.getName().equals(rdv.getCreatedBy())
                || currentConnectedUser.getName().equals(rdv.getModifiedBy())) {
            return true;
        }

        return false;
    }
}
