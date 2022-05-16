package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.ChartRacer;

import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 15/05/2022
 */
public class DrawingPane extends Pane {

    private final int xAxis;
    private final int yAxis;
    ChartRacer chartRacer = new ChartRacer();

    public DrawingPane(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.setLayoutX(this.xAxis);
        this.setLayoutY(this.yAxis);
    }

    public void addObjectsDrawingPane(Object javaFxObject){
        this.getChildren().add(((Node) javaFxObject));
    }

    public void clear() {
        this.getChildren().clear();
    }

    public Pane drawGraphic(List<String> specificYearData) {

        int xChartBar = 150;
        int yChartBar = 100;
        int width = 150;
        int height = 50;
        int yAxisCityName = 130;
        int xCityPopulation = 200;
        int yAxisLine = 60;

        //Sets Up Text About the Chart
        this.addObjectsDrawingPane(new Text(xAxis, yAxis,"Graphic that Represents the Demographic Population in Various Cities of the World in the Year " + chartRacer.getYear(specificYearData.get(0))));

        for (int i = 0; i < specificYearData.size(); i++) {

            chartRacer.getYear(specificYearData.get(i));
            String city = chartRacer.getCity(specificYearData.get(i));
            String country = chartRacer.getCountry(specificYearData.get(i));
            String population = chartRacer.getPopulationByCity(specificYearData.get(i));
            String region = chartRacer.getRegion(specificYearData.get(i));

            //Sets Up Lines to make the Graphic
            this.addObjectsDrawingPane(new LineChartRacer(150, yAxisLine, 150, 200));

            //Draws City Name
            this.addObjectsDrawingPane(new TextChartRacer(xAxis, yAxisCityName,city));

            //Sets Up the Bars of the Graphic
            //Makes Width according the Population Number
            double populationWidth = getBarWidthPopulation(population);
            RectangleChartRacer rectangle = new RectangleChartRacer(xChartBar, yChartBar, populationWidth, height);
            //Gets color generated automaticly
            rectangle.setColor(generateRandomColor());
            this.addObjectsDrawingPane(rectangle);

            this.addObjectsDrawingPane(new TextChartRacer(populationWidth + xCityPopulation, yAxisCityName,population));

            yAxisCityName += 70;
            yChartBar += 70;
            yAxisLine += 82;
        }
        //Adds the DrawingPane to super
        return this;
    }

    /**
     * Resume : Function that Generates a Random Color and returns a String of that Color
     * @return
     */
    private String generateRandomColor() {
        int r = (int) ((Math.random() * (255)) + 0);
        int g = (int) ((Math.random() * (255)) + 0);
        int b = (int) ((Math.random() * (255)) + 0);
        return r + "," + g + "," + b;
    }

    /**
     * Resume : Gets the With of the Bar Through the Population in the List
     * @param populationByCity
     * @return
     */
    private double getBarWidthPopulation(String populationByCity) {
        return Integer.parseInt(populationByCity.substring(populationByCity.length() - 3));
    }
}
