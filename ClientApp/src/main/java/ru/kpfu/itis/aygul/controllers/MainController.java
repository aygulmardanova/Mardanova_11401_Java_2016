package ru.kpfu.itis.aygul.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.kpfu.itis.aygul.connection.interfaces.ServerConnection;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;

import java.io.IOException;

/**
 * Created by aygulmardanova on 28.05.16.
 */
@Configurable
public class MainController {

    @Autowired
    ViewsLoader viewsLoader;

    @Autowired
    ServerConnection serverConnection;

    public void showClasses() throws IOException {
        viewsLoader.openPage("classes");
    }

}
