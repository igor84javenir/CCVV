package fr.asigroup.ccvv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class CcvvApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcvvApplication.class, args);
    }

}
