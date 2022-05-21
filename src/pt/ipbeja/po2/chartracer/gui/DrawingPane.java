package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pt.ipbeja.po2.chartracer.model.ChartRacer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 15/05/2022
 */
public class DrawingPane extends Pane {

    private final int xAxis;
    private final int yAxis;
    private ChartRacer chartRacer = new ChartRacer();


    public DrawingPane(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.setLayoutX(this.xAxis);
        this.setLayoutY(this.yAxis);
    }

    /*public void addObjectsDrawingPane(Object javaFxObject){
        this.getChildren().add(((Node) javaFxObject));
    }*/

    public void clear() {
        this.getChildren().clear();
    }

    public Pane drawGraphic(List<String> specificYearDataList) {

        int xChartBar = 150;
        int yChartBar = 100;
        int width = 150;
        int height = 70;
        int yAxisCityName = 120;
        int xCityPopulation = 200;
        int yAxisLine = 60;
        //Clears Pane
        this.getChildren().clear();
        //Loops Through String List and displays Data
        for (int i = 0; i < specificYearDataList.size(); i++) {
            //Sets Up Text About the Chart
            System.out.println("Ordered List = " + specificYearDataList.get(i));
            this.getChildren().add(new TextChartRacer(xAxis, yAxis, "Graphic that Represents the Demographic Population in Various Cities of the World in the Year " + chartRacer.getYear(specificYearDataList.get(i))));

            chartRacer.getYear(specificYearDataList.get(i));
            String city = chartRacer.getCity(specificYearDataList.get(i));
            String country = chartRacer.getCountry(specificYearDataList.get(i));
            String population = chartRacer.getPopulationByCity(specificYearDataList.get(i));
            String region = chartRacer.getRegion(specificYearDataList.get(i));

            //Sets Up Lines to make the Graphic
            this.getChildren().add(new LineChartRacer(150, yAxisLine, 150, 200));

            //Draws City Name
            this.getChildren().add(new TextChartRacer(xAxis, yAxisCityName, city + "\n" + country + "\n" + region));

            //Sets Up the Bars of the Graphic
            //Makes Width according the Population Number
            int populationWidth = getBarWidthPopulation(population);
            RectangleChartRacer rectangle = new RectangleChartRacer(xChartBar, yChartBar, populationWidth, height);
            //Gets color generated automaticly
            rectangle.setColor(generateRandomColor());
            this.getChildren().add(rectangle);
            this.getChildren().add(new TextChartRacer((int) (populationWidth + xCityPopulation), yAxisCityName + 17, population));

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
    public String generateRandomColor() {
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
    private int getBarWidthPopulation(String populationByCity) {
        //If the population number has more than 3 digits makes a new width
        if(populationByCity.length() == 4){
            return Integer.parseInt(populationByCity) / 10;
        } else if(populationByCity.length() == 5){
            return Integer.parseInt(populationByCity) / 50;
        }
        return Integer.parseInt(populationByCity);
    }
}
