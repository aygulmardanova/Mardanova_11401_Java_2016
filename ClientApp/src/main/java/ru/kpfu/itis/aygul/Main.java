package ru.kpfu.itis.aygul;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kpfu.itis.aygul.configuration.Config;
import ru.kpfu.itis.aygul.javafx.ViewsLoader;

/**
 * Start the client app for admin
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ViewsLoader viewsLoader = context.getBean(ViewsLoader.class);
        viewsLoader.openPage("login");
        primaryStage.setTitle("Fitness Club");
        primaryStage.setScene(viewsLoader.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
