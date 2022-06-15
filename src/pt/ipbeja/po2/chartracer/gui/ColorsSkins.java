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
public class ColorsSkins extends GraphicalSkins{

    private static double STROKE_WIDTH = 3.0;
    private static int height = 40;

    /**
     * Resume: Colors Skins Constructor
     * @param xChartBar
     * @param yChartBar
     * @param population
     */
    public ColorsSkins(int xChartBar, int yChartBar, int population) {
        super(xChartBar, yChartBar, population);
    }

    /**
     * Resume: Function that Generates Skin to this Skin
     * @return
     */
    @Override
    public List<List<Node>> generateSkin() {
        return new ArrayList<>(Collections.singleton(createColoredBar(xChartBar(), yChartBar(), population())));
    }

    /**
     * Resume: Function that Creates a Colored Bar
     * @param xChartBar
     * @param yChartBar
     * @param population
     * @return
     */
    public List<Node> createColoredBar(int xChartBar, int yChartBar, int population) {

        List<Node> squaresList = new ArrayList<>();

        RectangleChartRacer coloredBar = new RectangleChartRacer(xChartBar, yChartBar, population, height);
        coloredBar.setFill(Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber()));
        coloredBar.setStrokeWidth(STROKE_WIDTH);
        squaresList.add(coloredBar);

        return squaresList;
    }
}
