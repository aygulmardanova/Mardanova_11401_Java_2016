package ru.kpfu.itis.aygul.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.aygul.connection.ServerConnectionImpl;
import ru.kpfu.itis.aygul.controllers.LoginController;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;
import ru.kpfu.itis.aygul.javafx.SpringFXMLLoader;

/**
 * Beans configuration
 */


@Configuration
public class Config {

    @Bean
    LoginController loginController(){
        return new LoginController();
    }

    @Bean
    ViewsLoader viewsLoader(){
        return new ViewsLoader();
    }

    @Bean
    SpringFXMLLoader springFXMLLoader(){
        return new SpringFXMLLoader();
    }

    @Bean
    ServerConnectionImpl serverConnection(){
        return new ServerConnectionImpl();
    }

}
