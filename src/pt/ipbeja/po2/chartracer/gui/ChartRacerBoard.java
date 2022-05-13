package pt.ipbeja.po2.chartracer.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.ChartRacer;
import pt.ipbeja.po2.chartracer.model.View;

import java.io.File;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 11/05/2022
 */
public class ChartRacerBoard extends Pane implements View {

    //Creates a HBox
    HBox hBox = new HBox();
    //Sets up the View by passing "this" that extends from GridPane
    ChartRacer chartRacer = new ChartRacer(this);

    public ChartRacerBoard(Stage primaryStage) {

        //this.minWidth = minWidth;
        primaryStage.setMinWidth(getMaxWidth());
        primaryStage.setMinHeight(getMaxHeight());
        //Method that draws a Menu Bar with options
        createMenuBar(primaryStage);
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
        ComboBox<String> comboBox = new ComboBox(convertListToObservableList(allYearsList));

        //Creates a Button
        Button selectYearBtn = new Button("Select Year");

        //Sets the Hbox in Pane
        hBox.setLayoutX(50);
        hBox.setLayoutY(50);

        TextChartRacer textChartRacer = new TextChartRacer(50, 50, "Choose the Year You Want to See!! ");

        //Adds the Combobox and Button to HBox
        hBox.getChildren().addAll(textChartRacer, comboBox, selectYearBtn);
        //Adds HBox to Pane
        this.getChildren().add(hBox);

        //Click of The Button
        selectYearBtn.setOnAction(event -> {

            //Method that asks User File and asks user year and Draws the Chart
            createChart(userChoosenFile, comboBox.getValue());

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
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Graphic Operations");
        MenuItem menuItemDraw1Year = new MenuItem("Draw 1 Year");
        MenuItem menuItemDrawAllYears = new MenuItem("Draw All Years");

        //Checks If Item 1 has been Pressed
        //https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.MenuItem&method=setOnAction
        menuItemDraw1Year.setOnAction(event -> {

            //Calls the Function to Choose a File
            String userChoosenFile = askUserFile(primaryStage);
            //Asks the User with year they want to see
            askYearFile(userChoosenFile);
        });

        //Adds Items to Menu
        menu.getItems().addAll(menuItemDraw1Year, menuItemDrawAllYears);
        //Adds Menu to Menu Bar
        menuBar.getMenus().add(menu);
        //Adds Menu Bar To Program
        this.getChildren().add(menuBar);
    }

    private void createChart(String file, String year) {

        //Removes HBox from Pane
        hBox.getChildren().clear();

        //Gets the Data Relative to the Graphic and Draws it
        chartRacer.getDataToDrawGhraphic(chartRacer.getSpecificYearData(chartRacer.readFile(file), year));
    }

    @Override
    public void drawGraphic(List<String> specificYearData) {
        System.out.println("View Side 1500 List = " + specificYearData);

        //TODO - Make Variable Indexes so it's easier to displace items in pane
        // Do For to Loop Through List Elements
        int xAxis = 50;
        int yAxis = 50;
        int xChartBar = 150;
        int yChartBar = 100;
        int width = 150;
        int height = 50;
        int yAxisCityName = 130;
        int xCityPopulation = 350;
        int yAxisLine = 60;

        //Sets Up Text About the Chart
        this.getChildren().add(new Text(xAxis, yAxis,"Graphic that Represents the Demographic Population in Various Cities of the World in the Year " + chartRacer.getYear(specificYearData.get(0))));

        for (int i = 0; i < specificYearData.size(); i++) {

            //Sets Up Lines to make the Graphic
            this.getChildren().add(new LineChartRacer(150, yAxisLine, 150, 200));
            //specificYearData.get(i)

            //Draws City Name
            this.getChildren().add(new Text(xAxis, yAxisCityName,chartRacer.getCity(specificYearData.get(i))));

            //Sets Up the Bars of the Graphic
            //Makes Width according the Population Number
            this.getChildren().add(new RectangleChartRacer(xChartBar, yChartBar, getBarWidthPopulation(chartRacer.getPopulationByCity(specificYearData.get(i))), height));

            //Draws City Population
            this.getChildren().add(new Text(xCityPopulation, yAxisCityName,chartRacer.getPopulationByCity(specificYearData.get(i))));

            yAxisCityName += 70;
            yChartBar += 70;
            yAxisLine += 82;
        }
    }

    private double getBarWidthPopulation(String populationByCity) {
        System.out.println("Population By City = " + populationByCity);
        return Integer.parseInt(populationByCity.substring(populationByCity.length() - 3)) / 10;
    }
}
