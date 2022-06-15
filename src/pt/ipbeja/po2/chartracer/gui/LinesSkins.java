package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 27/05/2022
 */
public class LinesSkins extends GraphicalSkins{

    private static final int END_Y = 40;
    private final int lineWidth = 90;
    private final int BAR_NUM = 9;

    /**
     * Resume: Lines Skins Constructor
     * @param xChartBar
     * @param yChartBar
     * @param population
     */
    public LinesSkins(int xChartBar, int yChartBar, int population) {
        super(xChartBar, yChartBar, population);
    }

    /**
     * Resume: Function that generates Skin
     * @return: List of List of Nodes With Skin
     */
    @Override
    public List<List<Node>> generateSkin() {
        List<List<Node>> allBars = new ArrayList<>();
        List<Node> barLinesUpLeft = createBarLinesUpLeft(xChartBar(), yChartBar(), population());
        List<Node> barLinesUpRight = createBarLinesUpRight(xChartBar(), yChartBar(), population());
        List<Node> barLinesDownLeft = createBarLinesDownLeft(xChartBar(), yChartBar(), population());
        List<Node> barLinesDownRight = createBarLinesDownRight(xChartBar(), yChartBar(), population());

        allBars.add(barLinesUpLeft);
        allBars.add(barLinesUpRight);
        allBars.add(barLinesDownLeft);
        allBars.add(barLinesDownRight);
        //allBars.addAll(barLinesUpLeft, barLinesUpRight, barLinesDownLeft, barLinesDownRight);
        return allBars;
    }

    /**
     * Resume : Function that Creates Lines From Left to Up inside the RectangleBar
     * @param barX
     * @param barY
     * @param barWidth
     * @return: List of Lines UP Left From Lines Skins
     */
    public List<Node> createBarLinesUpLeft(int barX, int barY, int barWidth) {
        List<Node> lineList = new ArrayList<>();

        for (int i = 0; i < BAR_NUM; i++) {
            LineChartRacer line = new LineChartRacer(barX,barY, barWidth + lineWidth, barY + (5 * i));
            line.setStroke(Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber()));
            lineList.add(line);
        }
        return lineList;
    }

    /**
     * Resume : Function that Creates Lines From Right to Up inside the RectangleBar
     * @param barX
     * @param barY
     * @param barWidth
     * @return: List of Lines UP Right From Lines Skins
     */
    public List<Node> createBarLinesUpRight(int barX, int barY, int barWidth){
        List<Node> lineList = new ArrayList<>();

        for (int i = 0; i < BAR_NUM; i++) {
            LineChartRacer line = new LineChartRacer(barX,barY + (5 * i), barWidth + lineWidth, barY);
            line.setStroke(Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber()));
            lineList.add(line);
        }
        return lineList;
    }

    /**
     * Resume : Function that Creates Lines From Left to Down inside the RectangleBar
     * @param barX
     * @param barY
     * @param barWidth
     * @return: List of Lines DOWN Left From Lines Skins
     */
    public List<Node> createBarLinesDownLeft(int barX, int barY, int barWidth){
        List<Node> lineList = new ArrayList<>();

        for (int i = 0; i < BAR_NUM; i++) {
            LineChartRacer line = new LineChartRacer(barX,barY + END_Y, barWidth + lineWidth, barY + (5 * i));
            line.setStroke(Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber()));
            lineList.add(line);
        }
        return lineList;
    }

    /**
     * Resume : Function that Creates Lines From Right to Down inside the RectangleBar
     * @param barX
     * @param barY
     * @param barWidth
     * @return: List of Lines DOWN Right From Lines Skins
     */
    public List<Node> createBarLinesDownRight(int barX, int barY, int barWidth){
        List<Node> lineList = new ArrayList<>();

        for (int i = 0; i < BAR_NUM; i++) {
            LineChartRacer line = new LineChartRacer(barX,barY + (5 * i), barWidth + lineWidth, barY + END_Y);
            line.setStroke(Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber()));
            lineList.add(line);
        }
        return lineList;
    }
}
