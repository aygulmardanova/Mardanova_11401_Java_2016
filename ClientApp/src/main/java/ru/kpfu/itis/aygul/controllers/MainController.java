package ru.kpfu.itis.aygul.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.kpfu.itis.aygul.connection.interfaces.ServerConnection;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;
import ru.kpfu.itis.aygul.model.ClassClient;

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
    @Autowired
    ClassController classController;

    @FXML
    private TextField name;
    @FXML
    private TextArea description;
    @FXML
    private Label class_name;
    @FXML
    private TextArea edit_description;
    @FXML
    private Label message;

    public void showClasses() throws IOException {
        viewsLoader.openPage("classes");
    }

    public void classAddingPage() throws IOException {
        viewsLoader.openPage("add");
    }

    public void addClass() throws IOException {
        String className = name.getText();
        String classDescription = description.getText();
        if (serverConnection.addClass(className, classDescription)) {
            viewsLoader.openPage("classes");
            classController.setMessage("You have successfully added class " + className);
        } else {
            this.message.setText("Class " + className + " is already exists");
        }
    }

    public void editClass() throws IOException {
        String name = class_name.getText();
        String new_description = edit_description.getText();
        serverConnection.editClass(name, new_description);
        viewsLoader.openPage("classes");
        classController.setMessage("You have successfully edited class " + name);
    }

    public void openEditPage(ClassClient classClient) throws IOException {
        viewsLoader.openPage("edit");
        class_name.setText(classClient.getName());
        edit_description.setText(classClient.getDescription());
    }

    public void backToClasses() throws IOException {
        viewsLoader.openPage("classes");
    }

    public void backToMenu() throws IOException {
        viewsLoader.openPage("menu");
    }

}
