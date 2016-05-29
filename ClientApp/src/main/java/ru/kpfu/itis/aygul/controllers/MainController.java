package ru.kpfu.itis.aygul.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML
    TextField name;

    @FXML
    TextArea description;

    @FXML
    Label message;

    public void showClasses() throws IOException {
        viewsLoader.openPage("classes");
    }

    public void classAddingPage() throws IOException {
        viewsLoader.openPage("add");
    }

    public void addClass() {
        String className = name.getText();
        String classDescription = description.getText();
        if (serverConnection.addClass(className, classDescription)) {
            message.setText("You successfully added class " + className);
            message.setVisible(true);
        } else {
            message.setText("Class " + className + " is already exists");
        }
    }

    public void backToClasses() throws IOException {
        viewsLoader.openPage("classes");
    }

    public void backToMenu() throws IOException {
        viewsLoader.openPage("menu");
    }

    public void list() throws IOException {
        viewsLoader.openPage("list");
    }
}
