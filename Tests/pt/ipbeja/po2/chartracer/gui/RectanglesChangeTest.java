package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
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
        int barX = 50;
        int barY = 50;
        int barWidth = 500;
        int barHeight = 50;
        Rectangle rectangle = new Rectangle(barX, barY, barWidth, barHeight);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);

        Line line = new Line(barX,barY, barWidth + 50, barY + 10);
        line.setStroke(Color.YELLOW);

        Line line1 = new Line(barX,barY, barWidth + 50, barY + 20);
        line1.setStroke(Color. BLUE);

        Line line2 = new Line(barX,barY, barWidth + 50, barY + 30);
        line2.setStroke(Color.VIOLET);

        Line line3 = new Line(barX,barY, barWidth + 50, barY + 40);
        line3.setStroke(Color.WHITE);

        Line line4 = new Line(barX,barY, barWidth + 50, barY + 50);
        line4.setStroke(Color.GREEN);

        Line line5 = new Line(barX,barY + 50, barWidth + 50, barY);
        line5.setStroke(Color.GRAY);

        Line line6 = new Line(barX,barY + 40, barWidth + 50, barY);
        line6.setStroke(Color.FIREBRICK);

        Line line7 = new Line(barX,barY + 30, barWidth + 50, barY);
        line7.setStroke(Color.MIDNIGHTBLUE);

        Line line8 = new Line(barX,barY + 20, barWidth + 50, barY);
        line8.setStroke(Color.LIGHTBLUE);

        Line line9 = new Line(barX,barY + 10, barWidth + 50, barY);
        line9.setStroke(Color.DARKSALMON);

        Line line10 = new Line(barX,barY + 50, barWidth + 50, barY);
        line10.setStroke(Color.RED);

        Line line11 = new Line(barX,barY + 50, barWidth + 50, barY + 10);
        line11.setStroke(Color.RED);

        Line line12 = new Line(barX,barY + 50, barWidth + 50, barY + 20);
        line12.setStroke(Color.RED);

        Line line13 = new Line(barX,barY + 50, barWidth + 50, barY + 30);
        line13.setStroke(Color.RED);

        Line line14 = new Line(barX,barY + 50, barWidth + 50, barY + 40);
        line14.setStroke(Color.RED);

        Line line15 = new Line(barX,barY + 50, barWidth + 50, barY + 50);
        line15.setStroke(Color.RED);

        Line line16 = new Line(barX,barY, barWidth + 50, barY + 50);
        line16.setStroke(Color.YELLOW);

        Line line17 = new Line(barX,barY + 10, barWidth + 50, barY + 50);
        line17.setStroke(Color.YELLOW);

        Line line18 = new Line(barX,barY + 20, barWidth + 50, barY + 50);
        line18.setStroke(Color.YELLOW);

        Line line19 = new Line(barX,barY + 30, barWidth + 50, barY + 50);
        line19.setStroke(Color.YELLOW);

        Line line20 = new Line(barX,barY + 40, barWidth + 50, barY + 50);
        line20.setStroke(Color.YELLOW);

        Line line21 = new Line(barX,barY + 50, barWidth + 50, barY + 50);
        line21.setStroke(Color.YELLOW);

        List<Node> lineList = new ArrayList<>();
        lineList.add(line);
        lineList.add(line1);
        lineList.add(line2);
        lineList.add(line3);
        lineList.add(line4);
        lineList.add(line5);
        lineList.add(line6);
        lineList.add(line7);
        lineList.add(line8);
        lineList.add(line9);
        lineList.add(line10);
        lineList.add(line11);
        lineList.add(line12);
        lineList.add(line13);
        lineList.add(line14);
        lineList.add(line15);
        lineList.add(line16);
        lineList.add(line17);
        lineList.add(line18);
        lineList.add(line19);
        lineList.add(line20);
        lineList.add(line21);

        Pane pane = new Pane();
        //pane.getChildren().addAll(rectangle, line, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15, line16);
        pane.getChildren().add(rectangle);
        for (int i = 0; i < lineList.size(); i++) {
            pane.getChildren().add(lineList.get(i));
        }

        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
