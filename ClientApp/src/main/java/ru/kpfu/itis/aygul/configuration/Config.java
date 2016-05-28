package ru.kpfu.itis.aygul.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.aygul.connection.ServerConnectionImpl;
import ru.kpfu.itis.aygul.controllers.ClassController;
import ru.kpfu.itis.aygul.controllers.LoginController;
import ru.kpfu.itis.aygul.controllers.MainController;
import ru.kpfu.itis.aygul.javafx.SpringFXMLLoader;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;

/**
 * Beans configuration
 */

@Configuration
public class Config {

    @Bean
    Sample sample() {
        return new Sample();
    }

    @Bean
    MainController mainController() {
        return new MainController();
    }

    @Bean
    LoginController loginController(){
        return new LoginController();
    }

    @Bean
    ClassController classController() {
        return new ClassController();
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
