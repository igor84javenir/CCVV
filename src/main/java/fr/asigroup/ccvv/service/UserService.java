package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.pojo.PasswordsDTO;
import fr.asigroup.ccvv.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        if (user.getModifiedAt() == null) {
            user.setModifiedAt(LocalDateTime.now());
        }

        if (user.getCreatedBy() == null) {
            user.setCreatedBy("Creator Name");
        }
        if (user.getModifiedBy() == null) {
            user.setModifiedBy("Creator Name");
        }

        userRepository.save(user);
    }

    public void update(User user) {
        user.setModifiedBy("Modifier Name");
        user.setModifiedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public User getUserById(long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().isExist()) {
                return user.get();
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }


//    public List<User> getAll() throws UserNotFoundException {
//
//        Iterable<User> userIterable = userRepository.findAll();
//
//        List<User> users = new ArrayList<>();
//        for (User u : userIterable) {
//            if (u != null && u.isExist()) {
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

    public Page<User> getAllPagedExist(int pageNo, int rowsPerPage) {
        Pageable pageable = getPageable(pageNo, rowsPerPage);

        return userRepository.findAllByExist(true, pageable);
    }

    public Page<User> getAllPagedNotExist(int pageNo, int rowsPerPage) {
        Pageable pageable = getPageable(pageNo, rowsPerPage);

        return userRepository.findAllByExist(false, pageable);
    }

    private Pageable getPageable(int pageNo, int rowsPerPage) {
        return PageRequest.of(pageNo, rowsPerPage, Sort.by("modifiedAt"));
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

    public String updatePassword(long id, PasswordsDTO passwords) throws UserNotFoundException {
        User user = getUserById(id);

        if (!user.getPassword().equals(passwords.getOldPassword())) {
            return "Votre mot de passe actuel est incorrect";
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
