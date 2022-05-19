package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 19/05/2022
 */
public class RectanglesChangeTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creating a Target Node
        Rectangle rectangle = new Rectangle(50, 50, 100, 50);
        rectangle.setFill(Color.RED);

        Rectangle rectangle2 = new Rectangle(50, 100, 100, 50);
        rectangle2.setFill(Color.GREEN);

        Rectangle rectangle3 = new Rectangle(50, 150, 100, 50);
        rectangle3.setFill(Color.GREENYELLOW);

        Rectangle rectangle4 = new Rectangle(50, 200, 100, 50);
        rectangle4.setFill(Color.YELLOW);

        Rectangle rectangle5 = new Rectangle(50, 250, 100, 50);
        rectangle5.setFill(Color.DARKGRAY);

        Rectangle rectangle6 = new Rectangle(50, 300, 150, 50);
        rectangle6.setFill(Color.BLUE);

        Rectangle rectangle7 = new Rectangle(50, 350, 100, 50);
        rectangle7.setFill(Color.BLACK);

        Rectangle rectangle8 = new Rectangle(50, 400, 100, 50);
        rectangle8.setFill(Color.BLUEVIOLET);

        Rectangle rectangle9 = new Rectangle(50, 450, 100, 50);
        rectangle9.setFill(Color.BROWN);

        Rectangle rectangle10 = new Rectangle(50, 500, 100, 50);
        rectangle10.setFill(Color.LAVENDER);

        Rectangle rectangle11 = new Rectangle(50, 550, 100, 50);
        rectangle11.setFill(Color.AZURE);

        Rectangle rectangle12 = new Rectangle(50, 600, 100, 50);
        rectangle12.setFill(Color.DARKSALMON);

        List<Rectangle> rectangleList = new ArrayList<>();
        rectangleList.add(rectangle);
        rectangleList.add(rectangle2);
        rectangleList.add(rectangle3);
        rectangleList.add(rectangle4);
        rectangleList.add(rectangle5);
        rectangleList.add(rectangle6);
        rectangleList.add(rectangle7);
        rectangleList.add(rectangle8);
        rectangleList.add(rectangle9);
        rectangleList.add(rectangle10);
        rectangleList.add(rectangle11);
        rectangleList.add(rectangle12);


        Pane pane = new Pane();
        Rectangle rectanglePane = new Rectangle(0, 0, 70, 700);
        pane.getChildren().addAll(rectangle, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7, rectangle8, rectangle9, rectangle10, rectangle11, rectangle12 , rectanglePane);

        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();



        rectangle6.setY(50);
        rectangle5.setY(rectangle5.getY() + 50);
        rectangle4.setY(rectangle4.getY() + 50);
        rectangle3.setY(rectangle3.getY() + 50);
        rectangle2.setY(rectangle2.getY() + 50);
        rectangle.setY(rectangle.getY() + 50);


    }
}
