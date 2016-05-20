package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.model.Circle;
import sample.model.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by aygulmardanova on 20.05.16.
 */
public class TargetApp extends Application {

    //center - 368*268  (800*600)

    private int points;
    private int lives;
    private final int radius = 32;

    private Target getTarget(List<Target> targets, double x, double y) {
        for (Target target : targets) {
            if (target.getCircle().containsPoint(x, y)) {
                return target;
            }
        }
        return null;
    }

    //update Label value
    private void updateLabel(Label label, String type) {
        if ("points".equals(type)) {
            label.setText("Points: \n         " + points);
        } else {
            label.setText("Lives: \n         " + lives);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        lives = 5;
        points = 0;
        List<Target> targets;

        stage.setTitle("Target game");

        targets = new ArrayList<>();
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);


        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        Button exit = new Button("Quit the game");
        exit.setLayoutX(512);
        exit.setLayoutY(0);
        exit.setTextFill(Color.BLACK);
        exit.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
        exit.setStyle("-fx-background-color: gainsboro");
        exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        root.getChildren().add(exit);


        GraphicsContext gc = canvas.getGraphicsContext2D();

        Label pointsLabel = new Label();
        updateLabel(pointsLabel, "points");
        pointsLabel.setLayoutX(512);
        pointsLabel.setLayoutY(50);
        pointsLabel.setTextFill(Color.GAINSBORO);
        pointsLabel.setFont(Font.font("Cambria", 22));

        Label livesLabel = new Label();
        updateLabel(livesLabel, "lives");
        livesLabel.setLayoutX(512);
        livesLabel.setLayoutY(100);
        livesLabel.setTextFill(Color.GAINSBORO);
        livesLabel.setFont(Font.font("Cambria", 22));

        root.getChildren().addAll(pointsLabel, livesLabel);

        Image fon = new Image("/sample/images/jeans.jpg");
        gc.drawImage(fon, 0, 0);

        for (int i = 0; i < 5; i++) {
            Target target = new Target();
            target.setImage(new Image("/sample/images/target.png"));
            double x = i * 70 + radius;
            double y = i * 70 + radius;
            target.setCircle(new Circle(x, y, radius));
            targets.add(target);
            target.render(gc);
        }

        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        Target t = getTarget(targets, e.getX(), e.getY());
                        //if hit the target, points count will be calculated based on click's coordinates
                        if (t != null) {
                            System.out.print("Old points = " + points);
                            points += t.getCircle().getPoint(e.getX(), e.getY());
                            System.out.println("; Points = " + points);
                            updateLabel(pointsLabel, "points");
                            //if missed, lives count are decrease
                        } else {
                            lives--;
                            System.out.println("Lives = " + lives);
                            updateLabel(livesLabel, "lives");

                        }

                        //if player has no lives, he lost the game. He will see the alert message
                        //He will has an opportunity to choose between quit the game and starting new game
                        if (lives == 0) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText("You lost! You got " + points + " points.");
                            alert.setContentText("Start a new game or exit");

                            ButtonType restart = new ButtonType("Restart");
                            ButtonType quit = new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE);

                            alert.getButtonTypes().setAll(restart, quit);
                            Optional<ButtonType> result = alert.showAndWait();

                            if (result.get() == restart) {
                                lives = 5;
                                points = 0;
                                updateLabel(pointsLabel, "points");
                                updateLabel(livesLabel, "lives");
                            } else {
                                Platform.exit();
                            }
                        }

                    }
                });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
