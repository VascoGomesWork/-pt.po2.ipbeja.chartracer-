package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.layout.Pane;
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
    List<String> yearBeforeList = new ArrayList<>();
    List<String> oldColorList = new ArrayList<>();
    String[][] rectangleColorArray;
    String[][] rectangleCityArray;
    private ChartRacer chartRacer = new ChartRacer();
    int counter = 0;
    int yearsCounter = 0;
    int countryCounter = 0;
    int beforeListCounter = 0;


    public DrawingPane(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.setLayoutX(this.xAxis);
        this.setLayoutY(this.yAxis);
    }

    public void clear() {
        this.getChildren().clear();
    }

    public Pane drawGraphic(List<String> specificYearDataList, boolean checkFunctionAllYears) {

        int xChartBar = 150;
        int yChartBar = 50;
        int width = 150;
        int height = 50;
        int yAxisCityName = 80;
        int xCityPopulation = 200;
        int yAxisLine = 60;
        //Clears Pane
        this.getChildren().clear();
        //Loops Through String List and displays Data
        for (int i = 0; i < specificYearDataList.size(); i++) {

            //Sets Up Text About the Chart
            this.getChildren().add(new TextChartRacer(xAxis, yAxis, "Graphic that Represents the Demographic Population in Various Cities of the World in the Year " + chartRacer.getYear(specificYearDataList.get(i))));

            chartRacer.getYear(specificYearDataList.get(i));
            String city = chartRacer.getCity(specificYearDataList.get(i));
            String country = chartRacer.getCountry(specificYearDataList.get(i));
            String population = chartRacer.getPopulationByCity(specificYearDataList.get(i));
            String region = chartRacer.getRegion(specificYearDataList.get(i));

            //Sets Up Lines to make the Graphic
            this.getChildren().add(new LineChartRacer(150, yAxisLine, 150, 200));

            //Draws City Name
            this.getChildren().add(new TextChartRacer(xAxis, yAxisCityName, city));

            //Sets Up the Bars of the Graphic
            //Makes Width according the Population Number
            int populationWidth = getBarWidthPopulation(population, checkFunctionAllYears);
            RectangleChartRacer rectangle = new RectangleChartRacer(xChartBar, yChartBar, populationWidth, height);

            //Adds Elements to yearBeforeList and oldColorList
            chartRacer.getYear(specificYearDataList.get(i));
            yearBeforeList.add(chartRacer.getCity(specificYearDataList.get(i)));
            oldColorList.add(generateRandomColor());

            //Checks if it is AllYears Function that called if it wasn't generates a random color
            if(checkFunctionAllYears && counter > specificYearDataList.size())
                checkBarColor(specificYearDataList.subList(0, specificYearDataList.size()), i, rectangle, specificYearDataList.size());
            else rectangle.setColor(generateRandomColor());
            counter++;

            this.getChildren().add(rectangle);
            this.getChildren().add(new TextChartRacer((int) (populationWidth + xCityPopulation), yAxisCityName, population));

            yAxisCityName += 70;
            yChartBar += 70;
            yAxisLine += 82;
        }
        //Adds the DrawingPane to super
        return this;
    }

    /**
     * Resume : Function that gets the color to the graphic
     * @param specificYearDataList
     * @param i
     * @param rectangle
     * @param size
     */
    private void checkBarColor(List<String> specificYearDataList, int i, RectangleChartRacer rectangle, int size) {
            chartRacer.getYear(specificYearDataList.get(i));

            if (yearBeforeList.subList(0, size).contains(chartRacer.getCity(specificYearDataList.get(i)))) {
                String oldColor = oldColorList.get(i);
                rectangle.setColor(oldColor);
            } else {
                String newColor = generateRandomColor();
                oldColorList.add(newColor);
                rectangle.setColor(newColor);
            }

            if (i == size - 1) {
                yearBeforeList.subList(0, size - 1).clear();
                yearBeforeList.remove(0);
            }
        counter++;
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
     * @param checkFunctionAllYears
     * @return
     */
    private int getBarWidthPopulation(String populationByCity, boolean checkFunctionAllYears) {
        if(!checkFunctionAllYears){
            return Integer.parseInt(populationByCity);
        }
        return Integer.parseInt(populationByCity) / 30;
    }
}
