package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 27/05/2022
 */
public class SquaresSkins extends GraphicalSkins{

    private static double STROKE_WIDTH = 3.0;
    private static int height = 40;

    public SquaresSkins(int xChartBar, int yChartBar, int population) {
        super(xChartBar, yChartBar, population);
    }

    @Override
    public List<List<Node>> generateSkin() {
        return new ArrayList<>(Collections.singleton(createSquare(xChartBar(), yChartBar(), population())));
    }

    public List<Node> createSquare(int xChartBar, int yChartBar, int population) {

        List<Node> squaresList = new ArrayList<>();

        RectangleChartRacer square = new RectangleChartRacer(xChartBar, yChartBar, population, height);
        square.setFill(Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber()));
        //TODO - Set Stroke Width
        square.setStrokeWidth(STROKE_WIDTH);
        squaresList.add(square);

        return squaresList;
    }
}
