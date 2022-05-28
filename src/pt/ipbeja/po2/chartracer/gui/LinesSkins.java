package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 27/05/2022
 */
public class LinesSkins {

    private static final int END_Y = 40;
    private final int lineWidth = 90;
    private final int BAR_NUM = 9;


    /**
     * Resume : Function that Creates Lines From Left to Up inside the RectangleBar
     * @param barX
     * @param barY
     * @param barWidth
     * @return
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
     * @return
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
     * @return
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
     * @return
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

    /**
     * Resume : Function that Generates Random RGB Number Between 0 and 255
     * @return
     */
    private int generateRandRGBNumber(){
        return (int) ((Math.random() * (255)) + 0);
    }
}
