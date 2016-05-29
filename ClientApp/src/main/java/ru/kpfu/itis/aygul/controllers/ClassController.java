package ru.kpfu.itis.aygul.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.kpfu.itis.aygul.connection.interfaces.ServerConnection;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;
import ru.kpfu.itis.aygul.model.ClassClient;

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
    private void initialize() throws IOException {
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<ClassClient, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClassClient, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<ClassClient, String>("description"));

        tableClasses.setItems(classesData);
    }

    private void initData() throws IOException {
        classesData.addAll(serverConnection.getClasses());
    }

    public void goToMenu() throws IOException {
        viewsLoader.openPage("menu");
    }
}
