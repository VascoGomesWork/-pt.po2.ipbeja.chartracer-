package pt.ipbeja.po2.chartracer.gui;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.ChartRacer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vasco Gomes 19921
 * @date 15/05/2022
 */
public class DrawingPane extends Pane {

    private final int xAxis;
    private final int yAxis;
    private final int imageViewLayoutX = 5;
    private final int imageViewLayoutY = 30;
    private final int imageViewWidth = 100;
    private final int imageViewHeight = 100;
    private List<String> yearBeforeList = new ArrayList<>();
    private List<String> oldColorList = new ArrayList<>();
    private ChartRacer chartRacer = new ChartRacer();
    private int counter = 0;
    private boolean applySkin = false;
    private int cyclesCounter = 0;
    TranslateTransition translateTransition;
    ImageView imageView;
    RectangleChartRacer rectangle;
    TextChartRacer beginText;
    TextChartRacer cityNameText;
    TextChartRacer populationText;
    List<TextChartRacer> cityNameTextList = new ArrayList<>();
    List<TextChartRacer> populationTextList = new ArrayList<>();

    /**
     * Resume: Drawing Pane Constructor
     * @param xAxis
     * @param yAxis
     * @param cyclesCounter
     */
    public DrawingPane(int xAxis, int yAxis, int cyclesCounter) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.setLayoutX(this.xAxis);
        this.setLayoutY(this.yAxis);
        this.cyclesCounter = cyclesCounter;
    }

    /**
     * Resume: Function That Clears Drawing Pane
     */
    public void clear() {
        this.getChildren().clear();
    }

    /**
     * Resume: Get Bar Chart Racer Data to be Used in Draw Graphic Function
     * @param specificYearDataList
     * @param i
     * @return: barChartRacerData
     */
    private List<String> getBarChartRacerData(List<String> specificYearDataList, int i){
        //Creates new Array List
        List<String> barChartRacerDataList = new ArrayList<>();

        //Gets Data Necessary
        this.chartRacer.getYear(specificYearDataList.get(i));
        String city = this.chartRacer.getCity(specificYearDataList.get(i));
        this.chartRacer.getCountry(specificYearDataList.get(i));
        String population = this.chartRacer.getPopulationByCity(specificYearDataList.get(i));
        this.chartRacer.getRegion(specificYearDataList.get(i));

        //Adds City and Population to ArrayList
        barChartRacerDataList.add(city);
        barChartRacerDataList.add(population);

        return barChartRacerDataList;
    }

    /**
     * Resume: Function that Draws the Graphic
     * @param specificYearDataList
     * @param checkFunctionAllYears
     * @param applyLinesSkin
     * @param primaryStage
     * @param applySquaresSkin
     * @param cyclesCounter
     * @return
     */
    public Pane drawGraphic(List<String> specificYearDataList, boolean checkFunctionAllYears, boolean applyLinesSkin, Stage primaryStage, boolean applySquaresSkin, int cyclesCounter) {
        int xChartBar = 90;
        int yChartBar = 40;
        int height = 40;
        int yAxisCityName = yChartBar + 20;
        int xCityPopulation = 100;
        int yAxisLine = yChartBar;
        this.cyclesCounter = cyclesCounter;
        //Clears Pane
        this.getChildren().clear();
            //Loops Through String List and displays Data
            for (int i = 0; i < specificYearDataList.size(); i++) {

                //Sets Up Text About the Chart
                this.beginText = new TextChartRacer(this.xAxis, this.yAxis, "Graphic that Represents the Demographic Population in Various Cities of the World in the Year " + this.chartRacer.getYear(specificYearDataList.get(i)));
                this.getChildren().add(this.beginText);

                //Sets Up Lines to make the Graphic
                this.getChildren().add(new LineChartRacer(xChartBar, yAxisLine, xChartBar, xChartBar));

                //Gets Data of Bar Chart Racer to be Used in This Function
                List<String> barChartRacerData = getBarChartRacerData(specificYearDataList, i);

                //Draws City Name
                this.cityNameText = new TextChartRacer(this.xAxis, yAxisCityName, barChartRacerData.get(0));
                this.cityNameTextList.add(this.cityNameText);
                this.getChildren().add(this.cityNameText);

                //Sets Up the Bars of the Graphic
                //Makes Width according the Population Number
                int populationWidth = getBarWidthPopulation(barChartRacerData.get(1), primaryStage);

                //Creating a RectangleChartRacer
                createRectangle(xChartBar, yChartBar, height, populationWidth);

                //Checks if is Needed to Generate Color
                checkGenerateColor(specificYearDataList, checkFunctionAllYears, i, this.rectangle);

                checkIfSkinApplied(applyLinesSkin, applySquaresSkin, xChartBar, yChartBar, populationWidth);
                this.getChildren().add(rectangle);

                //Adds Elements to the Lists
                addElementsToList(specificYearDataList, i);

                this.populationText = new TextChartRacer(populationWidth + xCityPopulation, yAxisCityName, barChartRacerData.get(1));
                this.populationTextList.add(this.populationText);
                this.getChildren().add(this.populationText);

                yAxisCityName += 60;
                yChartBar += 60;
                yAxisLine += 60;
        }
        //Adds the DrawingPane to super
        return this;
    }

    /**
     * Resume: Function that Creates a Rectangle with the Atributes Needed
     * @param xChartBar
     * @param yChartBar
     * @param height
     * @param populationWidth
     */
    private void createRectangle(int xChartBar, int yChartBar, int height, int populationWidth) {
        this.rectangle = new RectangleChartRacer(xChartBar, yChartBar, populationWidth, height);
        this.rectangle.setStroke(Color.BLACK);
    }

    /**
     * Resume : Function that Checks if Any Skin is Applied
     * @param applyLinesSkin
     * @param applySquaresSkin
     * @param xChartBar
     * @param yChartBar
     * @param populationWidth
     */
    private void checkIfSkinApplied(boolean applyLinesSkin, boolean applySquaresSkin, int xChartBar, int yChartBar, int populationWidth) {
        if(applyLinesSkin){
            GraphicalSkins graphicalSkins = new LinesSkins(xChartBar, yChartBar, populationWidth);
            drawGraphicWithSkin(graphicalSkins);
        }
        if(applySquaresSkin){
            GraphicalSkins graphicalSkins = new ColorsSkins(xChartBar, yChartBar, populationWidth);
            drawGraphicWithSkin(graphicalSkins);
        }
    }

    /**
     * Resume : Function that Draws the Graphics
     * @param graphicalSkins
     */
    private void drawGraphicWithSkin(GraphicalSkins graphicalSkins) {
        this.rectangle.setFill(Color.TRANSPARENT);
        for (int i = 0; i < graphicalSkins.generateSkin().size(); i++) {
            for (int j = 0; j < graphicalSkins.generateSkin().get(i).size(); j++) {
                this.getChildren().add(graphicalSkins.generateSkin().get(i).get(j));
            }
        }
    }

    //TODO - FIX Bar Colors Without Skins
    /**
     * Function : Function that Checks if is needed to generate a new random color
     * @param specificYearDataList
     * @param checkFunctionAllYears
     * @param i
     * @param rectangle
     */
    private void checkGenerateColor(List<String> specificYearDataList, boolean checkFunctionAllYears, int i, RectangleChartRacer rectangle) {
        //Checks if it is AllYears Function that called if it wasn't generates a random color
        System.out.println("Counter = " + this.counter);
        System.out.println("Data = " + specificYearDataList);
        System.out.println("Size = " + specificYearDataList.size());
        if (checkFunctionAllYears && this.counter > specificYearDataList.size())
            checkBarColor(specificYearDataList.subList(0, specificYearDataList.size()), i, rectangle, specificYearDataList.size());
        else rectangle.setColor(generateRandomColor());
        this.counter++;
    }

    /**
     * Resume : Function that Adds Elements to Lists
     * @param specificYearDataList
     * @param i
     */
    private void addElementsToList(List<String> specificYearDataList, int i) {
        //Adds Elements to yearBeforeList and oldColorList
        this.chartRacer.getYear(specificYearDataList.get(i));
        this.yearBeforeList.add(this.chartRacer.getCity(specificYearDataList.get(i)));
        this.oldColorList.add(generateRandomColor());
    }

    /**
     * Resume : Function that gets the color to the graphic
     * @param specificYearDataList
     * @param i
     * @param rectangle
     * @param size
     */
    private void checkBarColor(List<String> specificYearDataList, int i, RectangleChartRacer rectangle, int size) {
        this.chartRacer.getYear(specificYearDataList.get(i));
        System.out.println("Teste");
        //Checks if Years Before List has the element if it has atributes it the same color, case not generates a new color
        if (this.yearBeforeList.subList(0, size).contains(this.chartRacer.getCity(specificYearDataList.get(i)))) {
            String oldColor = this.oldColorList.get(i);
            rectangle.setColor(oldColor);
        } else {
            String newColor = generateRandomColor();
            this.oldColorList.add(newColor);
            rectangle.setColor(newColor);
        }
        if (i == size - 1) {
            this.yearBeforeList.subList(0, size - 1).clear();
            this.yearBeforeList.remove(0);
        }
        this.counter++;
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
     * //@param primaryStage
     * @return
     */
    private int getBarWidthPopulation(String populationByCity, Stage primaryStage) {
        //Make Graphic Fit in any Window Size
        return (int) Math.sqrt(Integer.parseInt(populationByCity)) * (int) Math.sqrt(getIntegerPart(primaryStage.getWidth())) / 7;
    }

    /**
     * Resume : Function that animates an image
     */
    public Node animateImage(Stage primaryStage) {
        ImageView imageView = setUpImageView();
        Platform.runLater(() -> {
            makeTranslateTransition(this.imageView, primaryStage.getWidth(), primaryStage.getHeight());
        });
        return imageView;
    }

    /**
     * Resume : Function that Makes a Translate Transition
     * @param imageView
     * @param width
     * @param height
     */
    private void makeTranslateTransition(ImageView imageView, double width, double height) {
        //Translate Transition
        this.translateTransition = new TranslateTransition();
        this.translateTransition.setNode(imageView);
        this.translateTransition.setByX(width - (width / 4));
        this.translateTransition.setByY(height - (height / 4));
        this.translateTransition.setAutoReverse(true);
        //How to Control Animation Speed
        //https://stackoverflow.com/questions/28290814/how-to-slow-down-javafx-animation
        this.translateTransition.setRate(0.1);
        this.translateTransition.setCycleCount(Animation.INDEFINITE);
        this.translateTransition.play();
    }

    /**
     * Resume Function that Sets Up an Image View
     * @return
     */
    private ImageView setUpImageView() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("chart_png_mod.png")));
        this.imageView = new ImageView(image);
        this.imageView.setLayoutX(this.imageViewLayoutX);
        this.imageView.setLayoutY(this.imageViewLayoutY);
        this.imageView.setFitWidth(this.imageViewWidth);
        this.imageView.setFitHeight(this.imageViewHeight);
        this.imageView.setPreserveRatio(true);
        return this.imageView;
    }

    /**
     * Resume: Function that makes a FadeTransition
     */
    public void fadeTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(0.5);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setNode(this.imageView);
        fadeTransition.setCycleCount(2);
        fadeTransition.play();
    }

    /**
     * Resume: Function that Changes Graphic Theme to Dark Theme or Light Theme
     * @param color
     */
    public void changeTheme(Color color) {
        if(this.beginText != null){
            this.beginText.setFill(color);
        }

        for (TextChartRacer textChartRacer : this.cityNameTextList) {
            textChartRacer.setFill(color);
        }

        for (TextChartRacer textChartRacer : this.populationTextList) {
            textChartRacer.setFill(color);
        }
    }
}
