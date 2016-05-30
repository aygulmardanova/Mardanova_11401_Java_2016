package ru.kpfu.itis.aygul.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.kpfu.itis.aygul.connection.interfaces.ServerConnection;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;
import ru.kpfu.itis.aygul.model.ClassClient;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Controller for classes page
 */

@Configurable
public class ClassController {

    Logger log = Logger.getLogger(ClassController.class.getName());

    @Autowired
    public ViewsLoader viewsLoader;
    @Autowired
    MainController mainController;
    @Autowired
    public ServerConnection serverConnection;

    private ObservableList<ClassClient> classesData = FXCollections.observableArrayList();

    @FXML
    private TableView<ClassClient> tableClasses;
    @FXML
    private TableColumn<ClassClient, Integer> idColumn;
    @FXML
    private TableColumn<ClassClient, String> nameColumn;
    @FXML
    private TableColumn<ClassClient, String> descriptionColumn;
    @FXML
    private Label message;

    public void setMessage(String message) {
        this.message.setText(message);
    }
    @FXML
    private void initialize() throws IOException {
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<ClassClient, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClassClient, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<ClassClient, String>("description"));

        tableClasses.setItems(classesData);
    }

    private void initData() throws IOException {
        classesData.clear();
        classesData.addAll(serverConnection.getClasses());
    }

    public void goToMenu() throws IOException {
        viewsLoader.openPage("menu");
    }

    @FXML
    public void handleDeleteClass() throws IOException {
        int selectedIndex = tableClasses.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int selectedId = tableClasses.getSelectionModel().getSelectedItem().getId();
            log.info("deleting " + selectedId);
            serverConnection.deleteClass(selectedId);
            message.setText("You have deleted class " + tableClasses.getSelectionModel().getSelectedItem().getName());
            initialize();
        } else {
            showAlert();
        }
    }

    @FXML
    public void handleEditClass() throws IOException {
        int selectedIndex = tableClasses.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int selectedId = tableClasses.getSelectionModel().getSelectedItem().getId();
            log.info("editing " + selectedId);
            mainController.openEditPage(tableClasses.getSelectionModel().getSelectedItem());
        } else {
            showAlert();
        }
    }

    public void showAlert() {
        // Admin didn't select any class
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Class Selected");
        alert.setContentText("Please select a class in the table.");
        alert.showAndWait();
    }
}
