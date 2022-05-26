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
    private MenuBar menuBar;
    private int xAxis = 20;
    private int yAxis = 30;
    private DrawingPane drawingPane = new DrawingPane(xAxis,yAxis);
    //Sets up the View by passing "this" that extends from GridPane
    private ChartRacer chartRacer = new ChartRacer(this);
    private TextChartRacer textChartRacer;
    private boolean applySkin = false;
    private Stage primaryStage;

    public ChartRacerBoard(Stage primaryStage) {
        //Properties that Maximizes Primary Stage
        primaryStage.setMaximized(true);
        //Method that draws a Menu Bar with options
        createMenuBar(primaryStage);
        this.primaryStage = primaryStage;
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
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuBar.html
        //https://stackoverflow.com/questions/10315774/javafx-2-0-activating-a-menu-like-a-menuitem
        menuBar = new MenuBar();
        Menu menuGraphicOperations = new Menu("Graphic Operations");
        Menu menuSkins = new Menu("Skin");
        Menu menuProgramOperations = new Menu("Program Operations");

        MenuItem menuItemDraw1Year = new MenuItem("Draw 1 Year");
        MenuItem menuItemDrawAllYears = new MenuItem("Draw All Years");
        MenuItem menuItemClearAll = new MenuItem("Clear All");
        CheckMenuItem menuDarkMode = new CheckMenuItem("Dark Mode");
        MenuItem menuItemExit = new MenuItem("Exit");

        CheckMenuItem menuGraphicSkin1 = new CheckMenuItem("Line Bar Chart Skin");

        //OnClick of Menu Item "Draw 1 Year"
        //https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.MenuItem&method=setOnAction
        menuItemDraw1Year.setOnAction(event -> {

            //Clears Drawing Pane
            clear();

            //Calls the Function to Choose a File
            String userChoosenFile = askUserFile(primaryStage);
            //Asks the User with year they want to see
            askYearFile(userChoosenFile);
        });

        //OnClick of Menu Item "Draw All Years"
        menuItemDrawAllYears.setOnAction(event -> {

            //Clears Drawing Pane
            clear();

            //Loops through all the Years and Draws the Graphic with animations
            String userFile = askUserFile(primaryStage);

            drawAllYears(chartRacer.readFile(userFile), userFile);

        });

        //OnClick of Menu Item "Clear All"
        menuItemClearAll.setOnAction(event -> {
            // Clears HBox and Drawing Box
            clear();

        });

        //OnClick of Menu Item "Exit"
        menuItemExit.setOnAction(event -> {
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
        });

        //On Click in Check Menu Item Skin and check if it is selected
        //https://www.geeksforgeeks.org/javafx-checkmenuitem-with-examples/
        menuGraphicSkin1.setOnAction(event -> {
            if(menuGraphicSkin1.isSelected()) {
                applySkin = true;
            }else applySkin = false;
        });

        //On Click in Check Menu Item Dark Mode
        //https://www.geeksforgeeks.org/javafx-checkmenuitem-with-examples/
        menuDarkMode.setOnAction(event -> {
            if(menuDarkMode.isSelected()) {
                //TODO - Make Dark Mode
                super.setStyle("-fx-background-color: black;");
            } else if(!menuDarkMode.isSelected()){
                super.setStyle("-fx-background-color: white;");
            }
        });

        //Adds Items to Graphic Operations Menu
        menuGraphicOperations.getItems().addAll(menuItemDraw1Year, menuItemDrawAllYears, menuItemClearAll);
        //Adds Items to Skin Menu
        menuSkins.getItems().addAll(menuGraphicSkin1);
        //Adds Items to Program Operation Menu
        menuProgramOperations.getItems().addAll(menuDarkMode, menuItemExit);
        //Adds Menu to Menu Bar
        menuBar.getMenus().addAll(menuGraphicOperations, menuSkins, menuProgramOperations);
        //Adds Menu Bar To Program
        this.getChildren().add(menuBar);
    }

    /**
     * Resume : Function that clears hBox, drawing pane and sets another drawing pane
     */
    private void clear() {
        //TODO -Make Clear Function Stop JavaFx Threads
        hBox.getChildren().clear();

        //Clears the Drawing Box
        drawingPane.clear();

        //Resets the Drawing Box
        drawingPane = new DrawingPane(xAxis, yAxis);
    }

    /**
     * Resume : Function that clears Pane and Adds Menu Bar to Pane
     */
    private void clearPaneAddMenu(){
        //Clears Pane
        this.getChildren().clear();
        //Adds Menu Bar to the Pane
        this.getChildren().add(menuBar);

    }

    private void createChart(String file, String year) {

        //Removes HBox from Pane
        hBox.getChildren().clear();

        //Gets the Data Relative to the Graphic and Draws it
        chartRacer.getDataToDrawGraphic(chartRacer.getSpecificYearData(chartRacer.readFile(file), year));
    }

    @Override
    public void drawGraphic(List<String> specificYearData) {

        //Gets the Function to Draw the Graphic
        this.getChildren().add(drawingPane.drawGraphic(specificYearData, false, applySkin, primaryStage));

        //Creates new HBox Object
        hBox = new HBox();
    }

    @Override
    public void drawAllGraphics(List<String> yearBeforeList, List<String> orderedSpecificYearData) {
        List<String> oldColorList = new ArrayList<>();
        Platform.runLater(() -> {
            //Clears Pane so it dosent't throw a duplicate exeption error
            clearPaneAddMenu();

            //Adds to Pane the Drawing Pane with the information from other Thread
            this.getChildren().add(drawingPane.drawGraphic(orderedSpecificYearData, true, applySkin, primaryStage));
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
            //Check Witch Year the iteration is
            if(yearsCounter < qtyYearsInList) {
                yearDataChartRacer.add(chartRacer.getSpecificYearData(allYearsList, chartRacer.getAllYearsList(userFile).get(yearsCounter)));
            }
            yearsCounter++;
        }
        chartRacer.getDataDrawAllGraphics(yearDataChartRacer);
    }

}
