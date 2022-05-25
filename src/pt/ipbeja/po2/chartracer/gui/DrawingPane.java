package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    boolean applySkin = false;


    public DrawingPane(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.setLayoutX(this.xAxis);
        this.setLayoutY(this.yAxis);
    }

    public void clear() {
        this.getChildren().clear();
    }

    public Pane drawGraphic(List<String> specificYearDataList, boolean checkFunctionAllYears, boolean applySkin) {

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
        if(applySkin){
            drawGraphicSkin();
        }else {
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
                if (checkFunctionAllYears && counter > specificYearDataList.size())
                    checkBarColor(specificYearDataList.subList(0, specificYearDataList.size()), i, rectangle, specificYearDataList.size());
                else rectangle.setColor(generateRandomColor());
                counter++;

                this.getChildren().add(rectangle);
                this.getChildren().add(new TextChartRacer((int) (populationWidth + xCityPopulation), yAxisCityName, population));

                yAxisCityName += 70;
                yChartBar += 70;
                yAxisLine += 82;
            }
        }
        //Adds the DrawingPane to super
        return this;
    }


    private void drawGraphicSkin() {
        //Draws the Graphics with a Different Skin
        //TODO - Finish
        //createBarLinesUpLeft();

        //createBarLinesUpRight();

        //createBarLinesDownLeft();

        //createBarLinesDownRight();
    }

    private List<Node> createBarLinesUpLeft(int barX, int barY, int barWidth) {
        List<Node> lineList = new ArrayList<>();
        //Creates Bar Lines
        LineChartRacer line = new LineChartRacer(barX,barY, barWidth + 50, barY + 10);
        line.setStroke(Color.YELLOW);

        LineChartRacer line1 = new LineChartRacer(barX,barY, barWidth + 50, barY + 20);
        line1.setStroke(Color. BLUE);

        LineChartRacer line2 = new LineChartRacer(barX,barY, barWidth + 50, barY + 30);
        line2.setStroke(Color.VIOLET);

        LineChartRacer line3 = new LineChartRacer(barX,barY, barWidth + 50, barY + 40);
        line3.setStroke(Color.WHITE);

        LineChartRacer line4 = new LineChartRacer(barX,barY, barWidth + 50, barY + 50);
        line4.setStroke(Color.GREEN);

        //Adds Lines to Line List
        lineList.add(line);
        lineList.add(line1);
        lineList.add(line2);
        lineList.add(line3);
        lineList.add(line4);

        return lineList;
    }

    private List<Node> createBarLinesUpRight(int barX, int barY, int barWidth){
        List<Node> lineList = new ArrayList<>();
        LineChartRacer line5 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY);
        line5.setStroke(Color.GRAY);

        LineChartRacer line6 = new LineChartRacer(barX,barY + 40, barWidth + 50, barY);
        line6.setStroke(Color.FIREBRICK);

        LineChartRacer line7 = new LineChartRacer(barX,barY + 30, barWidth + 50, barY);
        line7.setStroke(Color.MIDNIGHTBLUE);

        LineChartRacer line8 = new LineChartRacer(barX,barY + 20, barWidth + 50, barY);
        line8.setStroke(Color.LIGHTBLUE);

        LineChartRacer line9 = new LineChartRacer(barX,barY + 10, barWidth + 50, barY);
        line9.setStroke(Color.DARKSALMON);

        //Adds Lines to Line List
        lineList.add(line5);
        lineList.add(line6);
        lineList.add(line7);
        lineList.add(line8);
        lineList.add(line9);

        return lineList;
    }

    private List<Node> createBarLinesDownLeft(int barX, int barY, int barWidth){
        List<Node> lineList = new ArrayList<>();
        LineChartRacer line10 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY);
        line10.setStroke(Color.RED);

        LineChartRacer line11 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY + 10);
        line11.setStroke(Color.RED);

        LineChartRacer line12 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY + 20);
        line12.setStroke(Color.RED);

        LineChartRacer line13 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY + 30);
        line13.setStroke(Color.RED);

        LineChartRacer line14 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY + 40);
        line14.setStroke(Color.RED);

        LineChartRacer line15 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY + 50);
        line15.setStroke(Color.RED);

        //Adds Lines to Line List
        lineList.add(line10);
        lineList.add(line11);
        lineList.add(line12);
        lineList.add(line13);
        lineList.add(line14);
        lineList.add(line15);

        return lineList;
    }

    private List<Node> createBarLinesDownRight(int barX, int barY, int barWidth){
        List<Node> lineList = new ArrayList<>();
        LineChartRacer line16 = new LineChartRacer(barX,barY, barWidth + 50, barY + 50);
        line16.setStroke(Color.YELLOW);

        LineChartRacer line17 = new LineChartRacer(barX,barY + 10, barWidth + 50, barY + 50);
        line17.setStroke(Color.YELLOW);

        LineChartRacer line18 = new LineChartRacer(barX,barY + 20, barWidth + 50, barY + 50);
        line18.setStroke(Color.YELLOW);

        LineChartRacer line19 = new LineChartRacer(barX,barY + 30, barWidth + 50, barY + 50);
        line19.setStroke(Color.YELLOW);

        LineChartRacer line20 = new LineChartRacer(barX,barY + 40, barWidth + 50, barY + 50);
        line20.setStroke(Color.YELLOW);

        LineChartRacer line21 = new LineChartRacer(barX,barY + 50, barWidth + 50, barY + 50);
        line21.setStroke(Color.YELLOW);

        //Adds Lines to Line List
        lineList.add(line16);
        lineList.add(line17);
        lineList.add(line18);
        lineList.add(line19);
        lineList.add(line20);
        lineList.add(line21);

        return lineList;
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
