package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 27/05/2022
 */
public class SquaresSkins {

    private static int POPULATION_DIVISION = 2;

    public List<Node> createSquare(int xChartBar, int yChartBar, int population, int height, Color color) {

        List<Node> squaresList = new ArrayList<>();

        for (int i = 0; i < POPULATION_DIVISION; i++) {
            RectangleChartRacer square = new RectangleChartRacer(xChartBar, yChartBar, population / 2, height);
            square.setFill(color);
            if(i > 0) {
                square.setFill(color.darker());
            }
            squaresList.add(square);
            xChartBar += xChartBar / 3;
        }
        return squaresList;
    }

    /**
     * Resume : Function that Generates Random RGB Number Between 0 and 255
     * @return
     */
    private int generateRandRGBNumber(){
        return (int) ((Math.random() * (255)) + 0);
    }
}
