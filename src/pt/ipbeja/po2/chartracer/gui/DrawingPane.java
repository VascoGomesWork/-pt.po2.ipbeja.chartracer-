package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pt.ipbeja.po2.chartracer.model.ChartRacer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 15/05/2022
 */
public class DrawingPane extends Pane {

    private static final int END_Y = 40;
    private final int xAxis;
    private final int yAxis;
    private List<String> yearBeforeList = new ArrayList<>();
    private List<String> oldColorList = new ArrayList<>();
    private int xChartBar = 90;
    private int yChartBar = 40;
    private int height = 40;
    private int yAxisCityName = yChartBar + 20;
    private int xCityPopulation = 100;
    private int yAxisLine = yChartBar;
    private ChartRacer chartRacer = new ChartRacer();
    private int counter = 0;
    private boolean applySkin = false;
    private final int lineWidth = 90;
    private final int BAR_NUM = 9;

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
                int populationWidth = getBarWidthPopulation(population, checkFunctionAllYears);

                //Creating a RectangleChartRacer
                RectangleChartRacer rectangle = new RectangleChartRacer(xChartBar, yChartBar, populationWidth, height);
                rectangle.setStroke(Color.BLACK);

                //Checks if is Needed to Generate Color
                checkGenerateColor(specificYearDataList, checkFunctionAllYears, i, rectangle);

                //Checks if Skin Check box is Checked
                if(applySkin){
                    //Puts the Color of the Rectangle Transparent
                    rectangle.setFill(Color.TRANSPARENT);
                    //Draws Graphic with Skin
                    drawGraphicSkin(xChartBar, yChartBar, populationWidth);
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
    private void drawGraphicSkin(int xChartBar, int yChartBar, int population) {
        //Draws the Graphics with a Different Skin Creating Lists of Nodes
        List<Node> barLinesUpLeft = createBarLinesUpLeft(xChartBar, yChartBar, population);
        List<Node> barLinesUpRight = createBarLinesUpRight(xChartBar, yChartBar, population);
        List<Node> barLinesDownLeft = createBarLinesDownLeft(xChartBar, yChartBar, population);
        List<Node> barLinesDownRight = createBarLinesDownRight(xChartBar, yChartBar, population);

        //Draws the Lists of Nodes in Drawing Pane
        for (Node node : barLinesUpLeft) {
            this.getChildren().add(node);
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
     * Resume : Function that Creates Lines From Left to Up inside the RectangleBar
     * @param barX
     * @param barY
     * @param barWidth
     * @return
     */
    private List<Node> createBarLinesUpLeft(int barX, int barY, int barWidth) {
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
    private List<Node> createBarLinesUpRight(int barX, int barY, int barWidth){
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
    private List<Node> createBarLinesDownLeft(int barX, int barY, int barWidth){
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
    private List<Node> createBarLinesDownRight(int barX, int barY, int barWidth){
        List<Node> lineList = new ArrayList<>();

        for (int i = 0; i < BAR_NUM; i++) {
            LineChartRacer line = new LineChartRacer(barX,barY + (5 * i), barWidth + lineWidth, barY + END_Y);
            line.setStroke(Color.rgb(generateRandRGBNumber(), generateRandRGBNumber(), generateRandRGBNumber()));
            lineList.add(line);
        }
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
     * Resume : Gets the With of the Bar Through the Population in the List
     * @param populationByCity
     * @param checkFunctionAllYears
     * @return
     */
    private int getBarWidthPopulation(String populationByCity, boolean checkFunctionAllYears) {
        //Todo - Get Fitted to Window Size
        if(!checkFunctionAllYears){
            return Integer.parseInt(populationByCity);
        }
        System.out.println("Window Size = " );
        return Integer.parseInt(populationByCity) / 35;
    }
}
