package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 27/05/2022
 */
public class TrianglesStartTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creating a Target Node
        int barX = 50;
        int barY = 50;
        int barWidth = 500;
        int barHeight = 50;

        List<Node> triangleList = new ArrayList<>();

        Rectangle rectangle = new Rectangle(barX, barY, barWidth, barHeight);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);

        Rectangle triangle = new Rectangle(barX, barY, barWidth, barHeight);
        triangle.setFill(Color.RED);
        triangle.setStroke(Color.BLACK);

        triangleList.add(triangle);

        Pane pane = new Pane();
        pane.getChildren().addAll(rectangle, triangle);

        barY += 60;

        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
