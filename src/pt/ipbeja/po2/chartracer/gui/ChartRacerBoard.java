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
import pt.ipbeja.po2.chartracer.model.ChartRacerModel;
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
    private final int xAxis = 20;
    private final int yAxis = 30;
    private final int hBoxXLayout = 200;
    private final int hBoxYLayout = 60;
    private final int hBoxXColorPicker = 700;
    private final int hBoxYColorPicker = 30;
    private final int NUM_MONTHS = 12;
    private List<String> yearBeforeListBoard = new ArrayList<>();
    private List<String> oldColorList = new ArrayList<>();
    private int cyclesCounter = 0;
    private DrawingPane drawingPane = new DrawingPane(xAxis,yAxis, cyclesCounter);
    //Sets up the View by passing "this" that extends from GridPane
    private ChartRacerModel chartRacerModel = new ChartRacerModel(this);
    private TextChartRacer textChartRacerChooseYear;
    private boolean applyLinesSkin = false;
    private boolean applyColorsSkin = false;
    private boolean generateStatisticFile = false;
    private Stage primaryStage;
    private Thread thread;
    Color colorToModify = Color.WHITE;
    private int counter = 0;

    /**
     * Resume: Chart Racer Board Constructor
     * @param primaryStage
     */
    public ChartRacerBoard(Stage primaryStage) {
        //Method that draws a Menu Bar with options
        createMenuBar(primaryStage);
        this.primaryStage = primaryStage;
        //Starts Animation
        this.getChildren().add(this.drawingPane.animateImage(primaryStage));
    }

    /**
     * Resume : Function that asks for a Year on a Specific File
     * @return
     * @param userChosenFile
     */
    private void askYearFile(String userChosenFile) {
        //Gets the File that the user Choose and Shows the Years Inside It for the User to Choose it
        List<String> allYearsList = this.chartRacerModel.getAllYearsList(userChosenFile);

        //Creates a Combobox with the Observable List Created from the Years List
        ComboBox<String> yearsComboBox = new ComboBox<>(convertListToObservableList(allYearsList));
        //Checks if Year List is Different from NUll
        if(allYearsList.size() > 0) {
            //https://stackoverflow.com/questions/34949422/how-to-set-default-value-in-combobox-javafx
            yearsComboBox.getSelectionModel().selectFirst();

            //Creates a Button
            Button selectYearBtn = new Button("Select Year");

            //Sets the Hbox in Pane
            this.hBox.setLayoutX(this.hBoxXLayout);
            this.hBox.setLayoutY(this.hBoxYLayout);

            this.textChartRacerChooseYear = new TextChartRacer(50, 50, "Choose the Year You Want to See!! ");
            if (this.chartRacerMenuBar.menuDarkMode.isSelected()) {
                this.textChartRacerChooseYear.setFill(this.colorToModify);
            }
            //Adds the Combobox and Button to HBox
            this.hBox.getChildren().addAll(this.textChartRacerChooseYear, yearsComboBox, selectYearBtn);
            //Adds HBox to Pane
            this.getChildren().add(this.hBox);

            //Click of The Button
            selectYearBtn.setOnAction(event -> {
                //Method that asks User File and asks user year and Draws the Chart
                createChart(userChosenFile, yearsComboBox.getValue());
            });
        } else this.hBox.getChildren().clear();
    }

    /**
     * Resume : Method that converts a List to an Observable List
     * @param allYearsList
     * @return: Observable List
     */
    private ObservableList<String> convertListToObservableList(List<String> allYearsList) {
        //https://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
        return FXCollections.observableList(allYearsList);
    }

    /**
     * Resume : Function that asks for the file to Read
     * @param primaryStage
     * @return: Filepath
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

    /**
     * Resume: Function that Creates a Menu
     * @param primaryStage
     */
    private void createMenuBar(Stage primaryStage) {
        this.chartRacerMenuBar = new ChartRacerMenuBar();
        //OnClick of Menu Item "Draw 1 Year"
        //https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.MenuItem&method=setOnAction
        this.chartRacerMenuBar.menuItemDraw1Year.setOnAction(event -> {
            menuBarDraw1Year(this.primaryStage);
        });

        //OnClick of Menu Item "Draw All Years"
        this.chartRacerMenuBar.menuItemDrawAllYears.setOnAction(event -> {
            menuBarDrawAllYears(this.primaryStage);
        });

        //OnClick of Menu Item "Clear All"
        this.chartRacerMenuBar.menuItemClearAll.setOnAction(event -> {
            menuBarClearAll(this.primaryStage);
        });

        //OnClick of Menu Item "Exit"
        this.chartRacerMenuBar.menuItemExit.setOnAction(event -> {
            menuBarExit();
        });

        //On Click in Check Menu Item Skin and check if it is selected
        //https://www.geeksforgeeks.org/javafx-checkmenuitem-with-examples/
        this.chartRacerMenuBar.menuGraphicLinesSkin.setOnAction(event -> {
            menuBarLinesGraphic(this.chartRacerMenuBar);
        });

        this.chartRacerMenuBar.menuGraphicColorsSkin.setOnAction(event -> {
            menuBarColorsGraphic(this.chartRacerMenuBar);
        });

        this.chartRacerMenuBar.menuData.setOnAction(event -> {
            menuBarGenerateDataFile(this.chartRacerMenuBar);
        });

        //On Click in Check Menu Item Dark Mode
        //https://www.geeksforgeeks.org/javafx-checkmenuitem-with-examples/
        this.chartRacerMenuBar.menuDarkMode.setOnAction(event -> {
            menuBarDarkMode(this.chartRacerMenuBar,this.colorToModify);
        });

        this.chartRacerMenuBar.menuChooseTextColor.setOnAction(event -> {
            makeColorPicker();
        });

        //Adds Menu Bar To Program
        this.getChildren().add(this.chartRacerMenuBar.getMenuBar());
    }

    /**
     * Resume: Creates a Color Picker to be Able to Change Graphic Colors
     */
    private void makeColorPicker() {
        //Creates a Button to Select the Color and another one to Close the Color Picker
        Button selectColor = new Button("Select Color");
        Button cancelColorPicker = new Button("Close Color Picker");

        HBox hBoxColorPicker = new HBox();
        hBoxColorPicker.setLayoutX(this.hBoxXColorPicker);
        hBoxColorPicker.setLayoutY(this.hBoxYColorPicker);
        //Creates a Color Picker
        //https://www.youtube.com/watch?v=81811wRFtF4
        ColorPicker colorPicker = new ColorPicker();
        hBoxColorPicker.getChildren().addAll(colorPicker, selectColor, cancelColorPicker);
        this.getChildren().add(hBoxColorPicker);

        selectColor.setOnAction(event -> {
            this.colorToModify = colorPicker.getValue();
        });

        cancelColorPicker.setOnAction(event -> {
            hBoxColorPicker.getChildren().clear();
        });
    }

    /**
     * Resume: Menu Bar DarkMode OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarDarkMode(ChartRacerMenuBar chartRacerMenuBar, Color color) {
        applyDarkMode(chartRacerMenuBar, color);
    }

    /**
     * Resume: Function that Really Applies Dark Mode Theme
     * @param chartRacerMenuBar
     */
    private void applyDarkMode(ChartRacerMenuBar chartRacerMenuBar, Color color) {
        //Checks if Dark Mode is Selected
        if(chartRacerMenuBar.menuDarkMode.isSelected()) {
            setUpDarkMode(color);
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
        if(this.drawingPane != null){
            this.drawingPane.changeTheme(color);
        }
    }

    /**
     * Resume: Menu Bar Generate Data File OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarGenerateDataFile(ChartRacerMenuBar chartRacerMenuBar) {
        if(chartRacerMenuBar.menuGenerateFile.isSelected()){
            this.generateStatisticFile = true;
        } else this.generateStatisticFile = false;
    }

    /**
     * Resume: Menu Bar Draw Skin With Colors OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarColorsGraphic(ChartRacerMenuBar chartRacerMenuBar) {
        //Checks if JavaFX Thread is Still Running if it ain't executes the code inside If statement
        if(!this.chartRacerModel.getThreadStatus()){
            if(chartRacerMenuBar.menuGraphicColorsSkin.isSelected() && !chartRacerMenuBar.menuGraphicLinesSkin.isSelected()){
                this.applyColorsSkin = true;
            } else{
                this.applyColorsSkin = false;
            }
        } else {
            //Raises an Alert about JavaFX Thread Still Running
            raiseThreadAliveAlert();
        }
    }

    /**
     * Resume: Menu Bar Draw Skin With Lines OnClick Function
     * @param chartRacerMenuBar
     */
    private void menuBarLinesGraphic(ChartRacerMenuBar chartRacerMenuBar) {
        //Checks if JavaFX Thread is Still Running if it ain't executes the code inside If statement
        if(!this.chartRacerModel.getThreadStatus()){
            if(chartRacerMenuBar.menuGraphicLinesSkin.isSelected() && !chartRacerMenuBar.menuGraphicColorsSkin.isSelected()) {
                this.applyLinesSkin = true;
            } else{
                this.applyLinesSkin = false;
            }
        } else {
            //Raises an Alert about JavaFX Thread Still Running
            raiseThreadAliveAlert();
        }
    }

    /**
     * Resume: Menu Bar Exit OnClick Function
     */
    private void menuBarExit() {
        //Buttons in Alert Type
        //https://stackoverflow.com/questions/43031602/how-to-set-a-method-to-a-javafx-alert-button
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ButtonType.html

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Are You Sure You Want to Exit?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit");
        Optional<ButtonType> btnResult = alert.showAndWait();

        //If Button Has Been Clicked Ends the Program
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
        this.getChildren().add(this.drawingPane.animateImage(primaryStage));
    }

    /**
     * Resume: Menu Bar Draw All Year OnClick Function
     * @param primaryStage
     */
    private void menuBarDrawAllYears(Stage primaryStage) {
        //Clears Drawing Pane
        this.clear();

        //Loops through all the Years and Draws the Graphic with animations
        String userFile = askUserFile(primaryStage);
        checksGenerateStatisticsFileActive(userFile);
        drawAllYears(this.chartRacerModel.readFile(userFile), userFile);
    }

    /**
     * Resume: Function that Generates a Statistics File if Boolean Value is Set to True
     * @param userFile
     */
    private void checksGenerateStatisticsFileActive(String userFile) {
        //If Generates Statistics Files Boolean Variable is True Then Executes Code Inside If
        if(this.generateStatisticFile){
            //Generates Statistics File
            this.chartRacerModel.generateStatisticFile(userFile);
        }
    }

    /**
     * Resume: Menu Bar Draw 1 Year OnClick Function
     * @param primaryStage
     */
    private void menuBarDraw1Year(Stage primaryStage) {
        //Checks if JavaFx Thread is Running and if it is Raises an Alert
        if(!this.chartRacerModel.getThreadStatus()) {
            //Clears Drawing Pane
            clear();
            //Calls the Function to Choose a File
            String userChosenFile = askUserFile(primaryStage);
            //Generates Statistic File
            checksGenerateStatisticsFileActive(userChosenFile);
            //Asks the User with year they want to see
            askYearFile(userChosenFile);
        } else {
            //Raises an Alert about JavaFX Thread Still Running
            raiseThreadAliveAlert();
        }
    }

    /**
     * Resume: Function that makes an alert if the JavaFX Thread is Running
     */
    private void raiseThreadAliveAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The JavaFX Thread is Running so This Option Cannot be Selected!!Please Wait Until Thread has Finished it's Execution");
        alert.setTitle("JavaFX Thread Information");
        alert.setHeaderText("JavaFX Thread Information");
        alert.showAndWait();
    }

    /**
     * Resume : Function that clears hBox, drawing pane and sets another drawing pane
     */
    private void clear() {
        //Stop Transitions
        if(this.drawingPane.translateTransition != null) {
            this.drawingPane.fadeTransition();
            this.drawingPane.translateTransition.stop();
        }

        //Checks if Thread is Null and if is alive if those conditions are true stops the thread
        if(this.thread != null && this.thread.isAlive()){
            this.thread.stop();
            this.chartRacerModel.setThreadStatus(false);
        }

        this.hBox.getChildren().clear();

        //Clears the Drawing Box
        this.drawingPane.clear();

        //Resets the Drawing Box
        this.drawingPane = new DrawingPane(this.xAxis, this.yAxis, cyclesCounter);
    }

    /**
     * Resume: Method that Creates a Graphic
     * @param file
     * @param year
     */
    private void createChart(String file, String year) {

        //Removes HBox from Pane
        this.hBox.getChildren().clear();

        //Gets the Data Relative to the Graphic and Draws it
        this.chartRacerModel.getDataToDrawGraphic(this.chartRacerModel.getSpecificYearData(this.chartRacerModel.readFile(file), year));
    }

    /**
     * Resume: Method From View to Draw 1 Graphic
     * @param specificYearData
     */
    @Override
    public void drawGraphic(List<String> specificYearData) {

        //Gets the Function to Draw the Graphic
        this.getChildren().add(this.drawingPane.drawGraphic(specificYearData, false, this.applyLinesSkin, this.primaryStage, this.applyColorsSkin, cyclesCounter, new ArrayList<>(), new ArrayList<>()));
        applyDarkMode(this.chartRacerMenuBar, this.colorToModify);

        //Creates new HBox Object
        this.hBox = new HBox();
    }


    /**
     * Resume: Method From View to Draw All Graphics
     * @param orderedSpecificYearData
     * @param thread
     */
    @Override
    public void drawAllGraphics(List<String> orderedSpecificYearData, Thread thread) {
        this.thread = thread;
        Platform.runLater(() -> {
            //Clears DrawingPane and Creates new DrawingPane Object so it dosent't throw a duplicate exeption error
            this.drawingPane.clear();
            this.drawingPane = new DrawingPane(this.xAxis, this.yAxis, cyclesCounter);

            //Function that FulFills Random Color List
            fulfillRandomColorList(this.oldColorList);

            //Adds to Pane the Drawing Pane with the information from other Thread
            this.getChildren().addAll(this.drawingPane.drawGraphic(orderedSpecificYearData, true, this.applyLinesSkin, this.primaryStage, this.applyColorsSkin, cyclesCounter, oldColorList, yearBeforeListBoard));
            this.cyclesCounter+=this.NUM_MONTHS;

            //Function that FulFills Old Year List
            fulfillOldYearList(orderedSpecificYearData);
            this.counter++;
            applyDarkMode(this.chartRacerMenuBar, this.colorToModify);
        });
        //Make Thread Sleep it can be possible to see the Graphics Passing Through
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resume : Function that Adds Elements to List
     * @param specificYearDataList
     */
    private void fulfillOldYearList(List<String> specificYearDataList) {
        //Adds Elements to yearBeforeList and oldColorList
        for (int i = 0; i < this.NUM_MONTHS; i++) {
            this.chartRacerModel.getYear(specificYearDataList.get(i));
            this.yearBeforeListBoard.add(this.chartRacerModel.getCity(specificYearDataList.get(i)));
        }
        if(this.counter > 0){
            yearBeforeListBoard.subList(0, this.NUM_MONTHS).clear();
        }
    }

    /**
     * Resume: Function that Adds Random Colors to List
     * @param oldColorList
     */
    private void fulfillRandomColorList(List<String> oldColorList) {
        if(this.counter == 0) {
            for (int i = 0; i < this.NUM_MONTHS; i++) {
                oldColorList.add(this.drawingPane.generateRandomColor());
            }
        }
    }

    /**
     * Resume : Loops through all the Years and Draws the Graphic with animations
     */
    private void drawAllYears(List<String> allYearsList, String userFile) {

        List<List<String>> yearDataChartRacer = new ArrayList<>();
        int yearsCounter = 0;
        int qtyYearsInList = this.chartRacerModel.getQtyYearsInList(allYearsList);
        if(allYearsList != null) {
            for (int i = 0; i < allYearsList.size(); i++) {
                //Check Witch Year the iteration is
                if (yearsCounter < qtyYearsInList) {
                    yearDataChartRacer.add(this.chartRacerModel.getSpecificYearData(allYearsList, this.chartRacerModel.getAllYearsList(userFile).get(yearsCounter)));
                }
                yearsCounter++;
            }
            this.chartRacerModel.getDataDrawAllGraphics(yearDataChartRacer);
        }
    }
}
