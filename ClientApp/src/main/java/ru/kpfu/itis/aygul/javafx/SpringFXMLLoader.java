package ru.kpfu.itis.aygul.javafx;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;

/**
 * SpringFXMLLoader
 */
public class SpringFXMLLoader {
    @Autowired
    private AnnotationConfigApplicationContext applicationContext;

    public <T> T load(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(applicationContext::getBean);
            return loader.load();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
