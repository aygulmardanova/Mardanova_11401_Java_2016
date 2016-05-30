package ru.kpfu.itis.aygul.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.kpfu.itis.aygul.connection.interfaces.ServerConnection;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;
import ru.kpfu.itis.aygul.model.UserClient;
import ru.kpfu.itis.aygul.model.enums.Role;

import java.io.IOException;

/**
 * Controller that check the authenticated user
 */
@Configurable
public class LoginController {

    @Autowired
    public ViewsLoader viewsLoader;
    @Autowired
    public ServerConnection serverConnection;

    @FXML
    Label failure;
    @FXML
    TextField login;
    @FXML
    TextField password;

    public void login() throws IOException {

        UserClient userClient = serverConnection.getUser(login.getText(), password.getText());
        if (userClient != null) {
            System.out.println(userClient);
        }

        if (userClient == null) {
            failure.setText("Incorrect login or password");
        } else if (!userClient.getRole().equals(Role.ROLE_ADMIN)) {
            failure.setText("Sorry, you aren't an admin");
        } else {
            viewsLoader.openPage("menu");
        }
    }

}
