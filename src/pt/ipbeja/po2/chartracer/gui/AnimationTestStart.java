package pt.ipbeja.po2.chartracer.gui;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Vasco Gomes 19921
 * @date 16/05/2022
 */
public class AnimationTestStart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creating a Target Node
        Rectangle rectangle = new Rectangle(50, 50, 50, 50);
        rectangle.setFill(Color.RED);

        Circle circle = new Circle();
        circle.setFill(Color.BLUE);
        circle.setRadius(30);
        circle.setLayoutX(50);
        circle.setLayoutY(50);

        //https://examples.javacodegeeks.com/desktop-java/javafx/javafx-transition-example/
        //Instantiate the Respective Transition Class
        ScaleTransition scaleTransition = new ScaleTransition();

        //Set the Desired Properties Like Duration, Cycle-Count, etc for the transition
        scaleTransition.setDuration(Duration.seconds(3));
        //scaleTransition.setToX(50);
        scaleTransition.setFromX(0);
        scaleTransition.setToX(30);
        //translateTransition.set

        //translateTransition.setToY(500);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);

        //Set the Target Node on Witch the Transition will be Applied.
        //Use the Following Method for this purpose
        scaleTransition.setNode(rectangle);

        //Finally, Play the Transition Using the Play Method.
        scaleTransition.play();

        Pane pane = new Pane();

        Rectangle rectanglePane = new Rectangle(0, 0, 70, 150);
        //rectanglePane.setStyle(pane.getStyle());
        //Get Background Color of Pane and Apply it to rectanglePane

        pane.getChildren().addAll(rectangle, rectanglePane);

        /*NewPaneTest pane1 = new NewPaneTest(50, 50);
        pane1.getChildren().add(pane);*/

        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
