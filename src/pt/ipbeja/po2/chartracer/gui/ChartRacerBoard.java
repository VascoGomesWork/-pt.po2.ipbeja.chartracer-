package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import pt.ipbeja.po2.chartracer.model.ChartRacer;
import pt.ipbeja.po2.chartracer.model.View;

import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 11/05/2022
 */
public class ChartRacerBoard extends GridPane implements View {

    private final int minWidth;
    //Sets up the View by passing "this" that extends from GridPane
    ChartRacer chartRacer = new ChartRacer(this);

    public ChartRacerBoard(int minWidth) {

        this.minWidth = minWidth;
        //TODO - Make method with a Menu bar with options
        createMenuBar(minWidth);

        //Method that creates the chart
        createChart();
    }

    private void createMenuBar(int barWidth) {
        MenuBar menuBar = new MenuBar();
        //menuBar.setWidth();
        MenuItem menuItem = new MenuItem("Draw 1 Year");
    }

    private void createChart() {

        String fileName = "src/pt/ipbeja/po2/chartracer/model/cities.txt";

        //TODO - Make method to ask the File

        //TODO - Make method to ask the year

        chartRacer.getDataToDrawGhraphic(chartRacer.getSpecificYearData(chartRacer.readFile(fileName), 1500+""));
    }

    @Override
    public void drawGraphic(List<String> specificYearData) {
        System.out.println("View Side 1500 List = " + specificYearData);
        this.getChildren().add(new Rectangle(0, 0, 150, 50));
        //this.getChildren().add(new Line(0, 0, 50, 50));
    }
}
