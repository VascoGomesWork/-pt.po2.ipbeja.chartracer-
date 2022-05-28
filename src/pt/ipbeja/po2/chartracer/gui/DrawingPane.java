package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    private List<String> yearBeforeList = new ArrayList<>();
    private List<String> oldColorList = new ArrayList<>();
    private ChartRacer chartRacer = new ChartRacer();
    private int counter = 0;
    private boolean applySkin = false;

    public DrawingPane(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.setLayoutX(this.xAxis);
        this.setLayoutY(this.yAxis);
    }

    public void clear() {
        this.getChildren().clear();
    }

    public Pane drawGraphic(List<String> specificYearDataList, boolean checkFunctionAllYears, boolean applyLinesSkin, Stage primaryStage, boolean applySquaresSkin) {
        int xChartBar = 90;
        int yChartBar = 40;
        int height = 40;
        int yAxisCityName = yChartBar + 20;
        int xCityPopulation = 100;
        int yAxisLine = yChartBar;

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
                this.getChildren().add(new LineChartRacer(xChartBar, yAxisLine, xChartBar, xChartBar));

                //Draws City Name
                this.getChildren().add(new TextChartRacer(xAxis, yAxisCityName, city));

                //Sets Up the Bars of the Graphic
                //Makes Width according the Population Number
                int populationWidth = getBarWidthPopulation(population, checkFunctionAllYears, primaryStage);

                //Creating a RectangleChartRacer
                RectangleChartRacer rectangle = new RectangleChartRacer(xChartBar, yChartBar, populationWidth, height);
                rectangle.setStroke(Color.BLACK);

                //Checks if is Needed to Generate Color
                checkGenerateColor(specificYearDataList, checkFunctionAllYears, i, rectangle);

                //Checks if Skin Check box is Checked
                if(applyLinesSkin){
                    //Puts the Color of the Rectangle Transparent
                    rectangle.setFill(Color.TRANSPARENT);
                    //Draws Graphic with Skin
                    drawGraphicSkinLines(xChartBar, yChartBar, populationWidth);
                } else if(applySquaresSkin){
                    Color color = Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber());
                    rectangle.setFill(Color.TRANSPARENT);
                    //Draws Graphic with Skin
                    drawGraphicSkinSquares(xChartBar, yChartBar, populationWidth, height, color);
                }
                this.getChildren().add(rectangle);

                //Adds Elements to the Lists
                addElementsToList(specificYearDataList, i);

                this.getChildren().add(new TextChartRacer(populationWidth + xCityPopulation, yAxisCityName, population));

                yAxisCityName += 60;
                yChartBar += 60;
                yAxisLine += 60;
        }
        //Adds the DrawingPane to super
        return this;
    }

    /**
     * Function : Function that Checks if is needed to generate a new random color
     * @param specificYearDataList
     * @param checkFunctionAllYears
     * @param i
     * @param rectangle
     */
    private void checkGenerateColor(List<String> specificYearDataList, boolean checkFunctionAllYears, int i, RectangleChartRacer rectangle) {
        //Checks if it is AllYears Function that called if it wasn't generates a random color
        if (checkFunctionAllYears && counter > specificYearDataList.size())
            checkBarColor(specificYearDataList.subList(0, specificYearDataList.size()), i, rectangle, specificYearDataList.size());
        else rectangle.setColor(generateRandomColor());
        counter++;
    }

    /**
     * Resume : Function that Adds Elements to Lists
     * @param specificYearDataList
     * @param i
     */
    private void addElementsToList(List<String> specificYearDataList, int i) {
        //Adds Elements to yearBeforeList and oldColorList
        chartRacer.getYear(specificYearDataList.get(i));
        yearBeforeList.add(chartRacer.getCity(specificYearDataList.get(i)));
        oldColorList.add(generateRandomColor());
    }

    /**
     * Resume : Function That Draws the Graphic with a Skin
     * @param xChartBar
     * @param yChartBar
     * @param population
     */
    public void drawGraphicSkinLines(int xChartBar, int yChartBar, int population) {
        //Draws the Graphics with a Different Skin Creating Lists of Nodes
        LinesSkins linesSkins = new LinesSkins();
        List<Node> barLinesUpLeft = linesSkins.createBarLinesUpLeft(xChartBar, yChartBar, population);
        List<Node> barLinesUpRight = linesSkins.createBarLinesUpRight(xChartBar, yChartBar, population);
        List<Node> barLinesDownLeft = linesSkins.createBarLinesDownLeft(xChartBar, yChartBar, population);
        List<Node> barLinesDownRight = linesSkins.createBarLinesDownRight(xChartBar, yChartBar, population);

        //Draws the Lists of Nodes in Drawing Pane
        for (Node node : barLinesUpLeft) {
            super.getChildren().add(node);
        }

        for (Node node : barLinesUpRight) {
            this.getChildren().add(node);
        }


        for (Node node : barLinesDownLeft) {
            this.getChildren().add(node);
        }

        for (Node node : barLinesDownRight) {
            this.getChildren().add(node);
        }
    }


    /**
     *
     */
    void drawGraphicSkinSquares(int xChartBar, int yChartBar, int population, int height, Color color){
        // TODO - APPly Polymorphism in Skins, make abstract Class
        SquaresSkins squaresSkins = new SquaresSkins();
        List<Node> squaresSkinsList = squaresSkins.createSquare(xChartBar, yChartBar, population, height, color);

        for (Node node : squaresSkinsList) {
            this.getChildren().add(node);
        }
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
     * Resume : Function that Generates Random RGB Number Between 0 and 255
     * @return
     */
    private int generateRandRGBNumber(){
        return (int) ((Math.random() * (255)) + 0);
    }

    /**
     * Resume : Function that Generates a Random Color and returns a String of that Color
     * @return
     */
    public String generateRandomColor() {
        int r = generateRandRGBNumber();
        int g = generateRandRGBNumber();
        int b = generateRandRGBNumber();
        return r + "," + g + "," + b;
    }

    /**
     * Resume : Function that Converts a Double to an Integer
     * @param doubleToConvertToInteger
     * @return
     */
    private int getIntegerPart(double doubleToConvertToInteger){
        String numToString = doubleToConvertToInteger+"";
        return Integer.parseInt(numToString.substring(0, numToString.indexOf(".")));
    }

    /**
     * Resume : Gets the With of the Bar Through the Population in the List
     * @param populationByCity
     * @param checkFunctionAllYears
     * //@param primaryStage
     * @return
     */
    private int getBarWidthPopulation(String populationByCity, boolean checkFunctionAllYears, Stage primaryStage) {
        //Make Graphic Fit in any Window Size
        return (int) Math.sqrt(Integer.parseInt(populationByCity)) * (int) Math.sqrt(getIntegerPart(primaryStage.getWidth())) / 7;
    }
}
