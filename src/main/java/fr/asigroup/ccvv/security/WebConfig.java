package fr.asigroup.ccvv.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/my-login").setViewName("signin/loginPage");
        registry.addViewController("/my-logout").setViewName("signin/logoutPage");
    }
}
