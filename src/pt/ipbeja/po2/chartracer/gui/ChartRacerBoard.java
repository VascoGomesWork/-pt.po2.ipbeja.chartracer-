package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.ChartRacer;
import pt.ipbeja.po2.chartracer.model.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Vasco Gomes 19921
 * @date 11/05/2022
 */
public class ChartRacerBoard extends Pane implements View {

    //Creates a HBox
    private HBox hBox = new HBox();
    ChartRacerMenuBar chartRacerMenuBar;
    MenuBar menuBar;
    private int xAxis = 20;
    private int yAxis = 30;
    private DrawingPane drawingPane = new DrawingPane(xAxis,yAxis);
    //Sets up the View by passing "this" that extends from GridPane
    private ChartRacer chartRacer = new ChartRacer(this);
    private TextChartRacer textChartRacer;
    private boolean applyLinesSkin = false;
    private boolean applyTrianglesSkin = false;
    private boolean generateStatisticFile = false;
    private Stage primaryStage;

    public ChartRacerBoard(Stage primaryStage) {
        //Method that draws a Menu Bar with options
        createMenuBar(primaryStage);
        this.primaryStage = primaryStage;
        System.out.println("X = " + primaryStage.getWidth());
        //Make animation
        this.getChildren().add(drawingPane.animateImage(primaryStage));
    }

    /**
     * Resume : Function that asks for a Year on a Specific File
     * @return
     * @param userChoosenFile
     */
    private void askYearFile(String userChoosenFile) {

        //Gets the File that the user Choose and Shows the Years Inside It for the User to Choose it
        List<String> allYearsList = chartRacer.getAllYearsList(userChoosenFile);

        //Creates a Combobox with the Observable List Created from the Years List
        ComboBox<String> yearsComboBox = new ComboBox<>(convertListToObservableList(allYearsList));
        //https://stackoverflow.com/questions/34949422/how-to-set-default-value-in-combobox-javafx
        yearsComboBox.getSelectionModel().selectFirst();

        //Creates a Button
        Button selectYearBtn = new Button("Select Year");

        //Sets the Hbox in Pane
        hBox.setLayoutX(200);
        hBox.setLayoutY(60);

        textChartRacer = new TextChartRacer(50, 50, "Choose the Year You Want to See!! ");

        //Adds the Combobox and Button to HBox
        hBox.getChildren().addAll(textChartRacer, yearsComboBox, selectYearBtn);
        //Adds HBox to Pane
        this.getChildren().add(hBox);

        //Click of The Button
        selectYearBtn.setOnAction(event -> {

            //Method that asks User File and asks user year and Draws the Chart
            createChart(userChoosenFile, yearsComboBox.getValue());

        });
    }

    /**
     * Resume : Method that converts a List to an Observable List
     * @param allYearsList
     * @return
     */
    private ObservableList<String> convertListToObservableList(List<String> allYearsList) {
        //https://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
        return FXCollections.observableList(allYearsList);
    }

    /**
     * Resume : Function that asks for the file to Read
     * @return
     * @param primaryStage
     */
    private String askUserFile(Stage primaryStage) {
        //https://openjfx.io/javadoc/17/javafx.graphics/javafx/stage/FileChooser.html
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text Files");
        //Sets Initial Directory to FileChooser
        fileChooser.setInitialDirectory(new File("src/pt/ipbeja/po2/chartracer/model"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        String filePath = "";
        //If File is Null shows an error

        if(selectedFile == null){
            System.out.println("ERROR : Did Not Choose any File!!");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Did Not Choose any File!!");
            alert.showAndWait();
        }
        else {
            //Gets the File Path
            filePath = selectedFile.getAbsolutePath();
        }
        return filePath;
    }

    private void createMenuBar(Stage primaryStage) {
        chartRacerMenuBar = new ChartRacerMenuBar();
        //OnClick of Menu Item "Draw 1 Year"
        //https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.MenuItem&method=setOnAction
        chartRacerMenuBar.menuItemDraw1Year.setOnAction(event -> {
            menuBarDraw1Year(primaryStage);
        });

        //OnClick of Menu Item "Draw All Years"
        chartRacerMenuBar.menuItemDrawAllYears.setOnAction(event -> {
            menuBarDrawAllYears(primaryStage);
        });

        //OnClick of Menu Item "Clear All"
        chartRacerMenuBar.menuItemClearAll.setOnAction(event -> {
            menuBarClearAll(primaryStage);
        });

        //OnClick of Menu Item "Exit"
        chartRacerMenuBar.menuItemExit.setOnAction(event -> {
            menuBarExit();
        });

        //On Click in Check Menu Item Skin and check if it is selected
        //https://www.geeksforgeeks.org/javafx-checkmenuitem-with-examples/
        chartRacerMenuBar.menuGraphicLinesSkin.setOnAction(event -> {
            menuBarLinesGraphic(chartRacerMenuBar);
        });

        chartRacerMenuBar.menuGraphicSquaresSkin.setOnAction(event -> {
            menuBarSquaresGraphic(chartRacerMenuBar);
        });

        chartRacerMenuBar.menuData.setOnAction(event -> {
            menuBarGenerateDataFile(chartRacerMenuBar);
        });

        //On Click in Check Menu Item Dark Mode
        //https://www.geeksforgeeks.org/javafx-checkmenuitem-with-examples/
        chartRacerMenuBar.menuDarkMode.setOnAction(event -> {
            menuBarDarkMode(chartRacerMenuBar);
        });

        //Adds Menu Bar To Program
        this.getChildren().add(chartRacerMenuBar.getMenuBar());
    }

    /**
     * Resume: Menu Bar DarkMode OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarDarkMode(ChartRacerMenuBar chartRacerMenuBar) {
        applyDarkMode(chartRacerMenuBar);
    }

    /**
     * Resume: Function that Really Applies Dark Mode Theme
     * @param chartRacerMenuBar
     */
    private void applyDarkMode(ChartRacerMenuBar chartRacerMenuBar) {
        //Checks if Dark Mode is Selected
        if(chartRacerMenuBar.menuDarkMode.isSelected()) {
            setUpDarkMode(Color.WHITE);
        } else if(!chartRacerMenuBar.menuDarkMode.isSelected()){
            setUpDarkMode(Color.BLACK);
            super.setStyle("-fx-background-color: white;");
        }
    }

    /**
     * Resume: Function that Sets Up Dark Mode
     * @param color
     */
    private void setUpDarkMode(Color color) {
        super.setStyle("-fx-background-color: black;");
        //Checks if Drawing Pane has been Created
        if(drawingPane != null){
            drawingPane.changeTheme(color);
        }
    }

    /**
     * Resume: Menu Bar Generate Data File OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarGenerateDataFile(ChartRacerMenuBar chartRacerMenuBar) {
        if(chartRacerMenuBar.menuGenerateFile.isSelected()){
            generateStatisticFile = true;
        } else generateStatisticFile = false;
    }

    /**
     * Resume: Menu Bar Draw Skin With Squares OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarSquaresGraphic(ChartRacerMenuBar chartRacerMenuBar) {
        if(chartRacerMenuBar.menuGraphicSquaresSkin.isSelected()){
            applyTrianglesSkin = true;
        } else applyTrianglesSkin = false;
    }

    /**
     * Resume: Menu Bar Draw Skin With Lines OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarLinesGraphic(ChartRacerMenuBar chartRacerMenuBar) {
        if(chartRacerMenuBar.menuGraphicLinesSkin.isSelected()) {
            applyLinesSkin = true;
        } else applyLinesSkin = false;
    }

    /**
     * Resume: Menu Bar Exit OnClick Function
     */
    private void menuBarExit() {
        //Buttons in Alert Type
        //https://stackoverflow.com/questions/43031602/how-to-set-a-method-to-a-javafx-alert-button
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ButtonType.html

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Are You Sure You Want to Exit?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> btnResult = alert.showAndWait();

        if(btnResult.isPresent() && btnResult.get() == ButtonType.YES){
            //How to Terminate an Application
            //https://stackoverflow.com/questions/12153622/how-to-close-a-javafx-application-on-window-close
            Platform.exit();
        }
    }

    /**
     * Resume: Menu Bar Clear All OnClick Function
     * @param primaryStage
     */
    private void menuBarClearAll(Stage primaryStage) {
        // Clears HBox and Drawing Box
        clear();
        //Make animation
        this.getChildren().add(drawingPane.animateImage(primaryStage));
    }

    /**
     * Resume: Menu Bar Draw All Year OnClick Function
     * @param primaryStage
     */
    private void menuBarDrawAllYears(Stage primaryStage) {
        //Clears Drawing Pane
        clear();

        //Loops through all the Years and Draws the Graphic with animations
        String userFile = askUserFile(primaryStage);
        //Generates Statistic File
        if(generateStatisticFile){
            //Generates Statistics File
            chartRacer.generateStatisticFile(userFile);
        }
        drawAllYears(chartRacer.readFile(userFile), userFile);
    }

    /**
     * Resume: Menu Bar Draw 1 Year OnClick Function
     * @param primaryStage
     */
    private void menuBarDraw1Year(Stage primaryStage) {
        //Clears Drawing Pane
        clear();

        //Calls the Function to Choose a File
        String userChoosenFile = askUserFile(primaryStage);
        //Generates Statistic File
        if(generateStatisticFile){
            //Generates Statistics File
            chartRacer.generateStatisticFile(userChoosenFile);
        }
        //Asks the User with year they want to see
        askYearFile(userChoosenFile);
    }

    /**
     * Resume : Function that clears hBox, drawing pane and sets another drawing pane
     */
    private void clear() {
        //Stop Transitions
        if(drawingPane.translateTransition != null) {
            drawingPane.fadeTransition();
            drawingPane.translateTransition.stop();
        }

        hBox.getChildren().clear();

        //Clears the Drawing Box
        drawingPane.clear();

        //Resets the Drawing Box
        drawingPane = new DrawingPane(xAxis, yAxis);
    }

    /**
     * Resume: Method that Creates a Graphic
     * @param file
     * @param year
     */
    private void createChart(String file, String year) {

        //Removes HBox from Pane
        hBox.getChildren().clear();

        //Gets the Data Relative to the Graphic and Draws it
        chartRacer.getDataToDrawGraphic(chartRacer.getSpecificYearData(chartRacer.readFile(file), year));
    }

    /**
     * Resume: Method From View to Draw 1 Graphic
     * @param specificYearData
     */
    @Override
    public void drawGraphic(List<String> specificYearData) {

        //Gets the Function to Draw the Graphic
        this.getChildren().add(drawingPane.drawGraphic(specificYearData, false, applyLinesSkin, primaryStage, applyTrianglesSkin));

        applyDarkMode(chartRacerMenuBar);

        //Creates new HBox Object
        hBox = new HBox();
    }

    /**
     * Resume: Method From View to Draw All Graphics
     * @param yearBeforeList
     * @param orderedSpecificYearData
     */
    @Override
    public void drawAllGraphics(List<String> yearBeforeList, List<String> orderedSpecificYearData) {
        List<String> oldColorList = new ArrayList<>();
        Platform.runLater(() -> {
            //Clears DrawingPane so it dosent't throw a duplicate exeption error
            drawingPane.clear();
            drawingPane = new DrawingPane(xAxis, yAxis);

            //Adds to Pane the Drawing Pane with the information from other Thread
            this.getChildren().add(drawingPane.drawGraphic(orderedSpecificYearData, true, applyLinesSkin, primaryStage, applyTrianglesSkin));

            applyDarkMode(chartRacerMenuBar);
        });
        //Make Thread Sleep it can be possible to see the Graphics Passing Through
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resume : Loops through all the Years and Draws the Graphic with animations
     */
    private void drawAllYears(List<String> allYearsList, String userFile) {

        List<List<String>> yearDataChartRacer = new ArrayList<>();
        //System.out.println("All Year List View Side = " + allYearsList);
        int yearsCounter = 0;
        int qtyYearsInList = chartRacer.getQtyYearsInList(allYearsList);
        for (int i = 0; i < allYearsList.size(); i++) {

            applyDarkMode(chartRacerMenuBar);

            //Check Witch Year the iteration is
            if(yearsCounter < qtyYearsInList) {
                yearDataChartRacer.add(chartRacer.getSpecificYearData(allYearsList, chartRacer.getAllYearsList(userFile).get(yearsCounter)));
            }
            yearsCounter++;
        }
        chartRacer.getDataDrawAllGraphics(yearDataChartRacer);
    }

}
