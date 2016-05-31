package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.model.Circle;
import sample.model.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by aygulmardanova on 20.05.16.
 * 
 * 7 levels in the game
 * After reaching the 7th level, game becomes endless
 * Level value determines the velocity of rotation
 * The more level value, the faster circles gyrate
 */
public class TargetApp extends Application {

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

    private int level;

    private int getLevel() {

        if (points < 300) {
            return points / 50 + 1;
        } else {
            return 300 / 50 + 1;
        }
//
//        if (points >= 100) {
//            return 3;
//        } else if (points >= 50) {
//            return 2;
//        }
//        return 1;
    }

    private double getCoef() {

        int level = getLevel();
        double coef = 1700000000.0 - level * 200000000.0;
        return coef;

//
//        switch (level) {
//            case 1:
//                return 1500000000.0;
//            case 2:
//                return 1000000000.0;
//        }
//        return 900000000.0;
    }

    //update Label value depending on the type value
    private void updateLabel(Label label, String type) {

        if ("points".equals(type)) {
            label.setText("Points: \n         " + points);
        } else if ("lives".equals(type)) {
            label.setText("Lives: \n         " + lives);
        } else if ("level".equals(type)) {
            if (level != getLevel()) {
                showLevelDialog();
            }
            level = getLevel();
            label.setText("   " + level + " level");
        } else {
            String login = "";

            TextInputDialog dialog = new TextInputDialog("login");
            dialog.setTitle("Enter dialog");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter your login:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                login = result.get();
            } else {
                Platform.exit();
            }
            label.setText("     " + login);
        }
    }

    private void showLevelDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Level changed");
        alert.setHeaderText(null);
        alert.setContentText("You reached the point of " + getLevel() + " level!");

        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws Exception {

        lives = 5;
        points = 0;
        level = 1;
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


        Label loginLabel = new Label();
        updateLabel(loginLabel, "login");
        loginLabel.setLayoutX(512);
        loginLabel.setLayoutY(50);
        loginLabel.setTextFill(Color.GAINSBORO);
        loginLabel.setFont(Font.font("Cambria", 22));

        Label levelLabel = new Label();
        updateLabel(levelLabel, "level");
        levelLabel.setLayoutX(512);
        levelLabel.setLayoutY(100);
        levelLabel.setTextFill(Color.GAINSBORO);
        levelLabel.setFont(Font.font("Cambria", 22));

        Label pointsLabel = new Label();
        updateLabel(pointsLabel, "points");
        pointsLabel.setLayoutX(512);
        pointsLabel.setLayoutY(150);
        pointsLabel.setTextFill(Color.GAINSBORO);
        pointsLabel.setFont(Font.font("Cambria", 22));

        Label livesLabel = new Label();
        updateLabel(livesLabel, "lives");
        livesLabel.setLayoutX(512);
        livesLabel.setLayoutY(200);
        livesLabel.setTextFill(Color.GAINSBORO);
        livesLabel.setFont(Font.font("Cambria", 22));


        VBox vbox = new VBox();
        vbox.setLayoutX(130);
        vbox.setLayoutY(225);

        final String content = "Start the game";
        final Text text = new Text(10, 20, "");
        text.setFont(Font.font("Cambria", FontWeight.BOLD, 40));

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
            }
        };

        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Group) scene.getRoot()).getChildren().remove(vbox);
            }
        });

        vbox.getChildren().add(text);
        vbox.setSpacing(10);

        animation.play();

        ((Group) scene.getRoot()).getChildren().add(vbox);

        root.getChildren().addAll(loginLabel, levelLabel, pointsLabel, livesLabel);

        GraphicsContext gc = canvas.getGraphicsContext2D();

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

        final long startNanoTime = System.nanoTime();

        //Circular rotation
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double coef = getCoef();
                double t = (currentNanoTime - startNanoTime) / coef;
                // background image clears canvas
                gc.drawImage(fon, 0, 0);
                for (int i = 0; i < 5; i++) {
                    double x = 64 * (2 + i) + 96 * Math.cos(t);
                    double y = 64 * (2 + i) + 96 * Math.sin(t);
                    targets.get(i).getCircle().setCenter(x, y);
                    targets.get(i).render(gc);
                }
            }
        }.start();


        //if hit the target, points count will be calculated based on click's coordinates
        //if missed, lives count is decreases
        scene.setOnMouseClicked(e -> {
            Target t = getTarget(targets, e.getX(), e.getY());
            if (t != null) {
                System.out.print("Old points = " + points);
                points += t.getCircle().getPoint(e.getX(), e.getY());
                System.out.println("; Points = " + points);
                System.out.println("coef: " + getCoef());
                updateLabel(pointsLabel, "points");
                updateLabel(levelLabel, "level");
            } else {
                lives--;
                System.out.println("Lives = " + lives);
                updateLabel(livesLabel, "lives");

            }

            //if player has no lives, he lost the game. He will see the alert message
            //He will has an opportunity to choose between quit the game and starting new game
            if (lives == 0) {
                System.out.println("You lost!");
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
                    level = 1;
                    updateLabel(loginLabel, "login");
                    updateLabel(levelLabel, "level");
                    updateLabel(pointsLabel, "points");
                    updateLabel(livesLabel, "lives");

                    animation.play();
                    ((Group) scene.getRoot()).getChildren().add(vbox);
                } else {
                    Platform.exit();
                }
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
