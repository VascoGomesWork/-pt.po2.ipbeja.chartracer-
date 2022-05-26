package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

/**
 * @author Vasco Gomes 19921
 * @date 09/05/2022
 */
public class ChartRacerStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int minHeight = 200;
        int minWidth = 400;
        //TODO - Reduce Method With more than 30 ;
        ChartRacerBoard board = new ChartRacerBoard(primaryStage);
        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chart Racer - Trabalho Prático de Programação Orientada a Objetos por: Vasco Gomes Nº19921");
        primaryStage.setMinHeight(minHeight);
        primaryStage.setMinWidth(minWidth);
        //Sets an Application Icon
        //https://kensoftph.com/change-the-stage-icon-in-javafx/
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("bar_chart_icon2.png"))));
        primaryStage.show();
    }
}
