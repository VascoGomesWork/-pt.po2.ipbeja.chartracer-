package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        //TODO - Execute Program with Java 17 not Java 8
        ChartRacerBoard board = new ChartRacerBoard(minWidth);
        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chart Racer - Trabalho Prático de Programação Orientada a Objetos Nº19921");
        primaryStage.setMinHeight(minHeight);
        primaryStage.setMinWidth(minWidth);
        primaryStage.show();
    }
}
