package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Vasco Gomes 19921
 * @date 11/05/2022
 */
public class RectangleChartRacer extends Rectangle {

    public RectangleChartRacer(double x, double y, double width, double height) {
        super(x, y, width, height);
    }


    public void setColor(String color){
        //Sets Colors through RGB Values
        //Sets Background Color
        int r = Integer.parseInt(color.substring(0, color.indexOf(',')));
        int g = Integer.parseInt(color.substring(color.indexOf(',') + 1, color.lastIndexOf(',')));
        int b = Integer.parseInt(color.substring(color.lastIndexOf(',') + 1));
        this.setFill(Color.rgb(r, g, b));

        //Sets Stroke
        this.setStroke(Color.rgb(0,0,0));
    }

}
