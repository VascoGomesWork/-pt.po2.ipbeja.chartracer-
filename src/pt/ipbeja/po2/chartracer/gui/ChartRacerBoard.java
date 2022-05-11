package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.layout.GridPane;
import pt.ipbeja.po2.chartracer.model.ChartRacer;
import pt.ipbeja.po2.chartracer.model.View;

import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 11/05/2022
 */
public class ChartRacerBoard extends GridPane implements View {

    //Sets up the View by passing "this" that extends from GridPane
    ChartRacer chartRacer = new ChartRacer(this);

    public ChartRacerBoard() {

        //TODO - Make method with a Menu bar with options

        //Method that creates the chart
        createChart();
    }

    private void createChart() {

        String fileName = "src/pt/ipbeja/po2/chartracer/model/cities.txt";

        //TODO - Make method to ask the File

        //TODO - Make method to ask the year

        chartRacer.getDataDrawGhraphic(chartRacer.getSpecificYearData(chartRacer.readFile(fileName), 1500+""));
    }

    @Override
    public void drawGraphic(List<String> specificYearData) {
        System.out.println("View Side 1500 List = " + specificYearData);
        //this.getChildren().add(new Line(0, 0, 50, 50));
    }
}
