package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.pojo.PasswordsDTO;
import fr.asigroup.ccvv.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private UserRepository userRepository;

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>€]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String save(User user) {
        if (userRepository.findByName(user.getName()) != null) {
            return "Un utilisateur de nom '" + user.getName() + "' existe déjà. Utilisez un autre nom";
        }

        if (user.getName().isBlank() || user.getMail().isBlank()) {
            return "Tous les champs sont obligatoires";
        }

        if (!isValid(user.getPassword())) {
            return "Le mot de passe doit contenir au moins un chiffre [0-9], "
                    + "un caractère latin minuscule [a-z], un majuscule [A-Z], "
                    + "un caractère spécial [!, @, #, &, (, ), etc.] et d'avoir une longueur "
                    + "de 8 à 20 caractères";
        }


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));


        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        if (user.getCreatedBy() == null) {
            user.setCreatedBy(auth.getName());
        }

        user.setModifiedAt(LocalDateTime.now());

        user.setModifiedBy(auth.getName());


        userRepository.save(user);

        return "Le compte '" + user.getName() + "' a été créé avec succès";
    }

    public String update(User user) {
        if (userRepository.findByName(user.getName()) != null && !userRepository.findById(user.getId()).get().getName().equals(user.getName())) {
            return "Un utilisateur de nom '" + user.getName() + "' existe déjà. Utilisez un autre nom";
        }

        if (user.getName().isBlank() || user.getMail().isBlank()) {
            return "Tous les champs sont obligatoires";
        }


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        user.setModifiedBy(auth.getName());

        user.setModifiedAt(LocalDateTime.now());

        userRepository.save(user);

        return "Le compte '" + user.getName() + "' a été modifié avec succès";
    }

    public User getUserById(long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().isExist()) {
                return user.get();
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }

    public User getUserByName(String userName) throws UserNotFoundException {
        User user = userRepository.findByName(userName);
        if (user != null && user.isExist()) {
            return user;
        } else {
            throw new UserNotFoundException("User with name " + userName + " not found");
        }
    }


    public List<User> getAll() throws UserNotFoundException {

        Iterable<User> userIterable = userRepository.findAll();

        List<User> users = new ArrayList<>();
        for (User u : userIterable) {
            if (u != null && u.isExist()) {
                users.add(u);
            }
        }

        if (!users.isEmpty()) {
            return users;
        } else {
            throw new UserNotFoundException("No users found");
        }
    }

    public Page<User> getAllPagedExist(int pageNo, int rowsPerPage) {
        Pageable pageable = getPageable(pageNo, rowsPerPage);

        return userRepository.findAllByExist(true, pageable);
    }

    public Page<User> getAllPagedNotExist(int pageNo, int rowsPerPage) {
        Pageable pageable = getPageable(pageNo, rowsPerPage);

        return userRepository.findAllByExist(false, pageable);
    }

    private Pageable getPageable(int pageNo, int rowsPerPage) {
        return PageRequest.of(pageNo, rowsPerPage, Sort.by("modifiedAt").descending());
    }

//    public List<User> getAllDeactivated() throws UserNotFoundException {
//        Iterable<User> userIterable = userRepository.findAll();
//
//        List<User> users = new ArrayList<>();
//        for (User u : userIterable) {
//            if (u != null && !u.isExist()) {
//                users.add(u);
//            }
//        }
//
//        if (!users.isEmpty()) {
//            return users;
//        } else {
//            throw new UserNotFoundException("No users found");
//        }
//    }

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String updatePassword(PasswordsDTO passwords) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByName(auth.getName());

        System.out.println(isValid(passwords.getNewPassword()));

        if (!BCrypt.checkpw(passwords.getOldPassword(), user.getPassword())) {
            return "Votre mot de passe actuel est incorrect";
        }

        if (!isValid(passwords.getNewPassword())) {
            return "Le mot de passe doit contenir au moins un chiffre [0-9], "
                    + "un caractère latin minuscule [a-z], un majuscule [A-Z], "
                    + "un caractère spécial [!, @, #, &, (, ), etc.] et d'avoir une longueur "
                    + "de 8 à 20 caractères";
        }

        if (passwords.getNewPassword() != null && passwords.getConfirmationPassword() != null && !passwords.getNewPassword().isEmpty() && !passwords.getConfirmationPassword().isEmpty()) {
            if (passwords.getNewPassword().equals(passwords.getConfirmationPassword())) {
                user.setPassword(passwords.getNewPassword());
                save(user);
                return "Votre mot de passe a été modifié avec succès";
            } {
                return "Les champs \"Nouvea mot de passe\" et \"Confirmer mot de passe\" doivent etre identiques";
            }

        } else {
            return "Tous les champs sont obligatoires";
        }


    }


}
