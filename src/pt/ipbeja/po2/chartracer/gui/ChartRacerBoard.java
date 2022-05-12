package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

    private final int minWidth;
    //Sets up the View by passing "this" that extends from GridPane
    ChartRacer chartRacer = new ChartRacer(this);

    public ChartRacerBoard(int minWidth, Stage primaryStage) {

        this.minWidth = minWidth;
        //Method that draws a Menu Bar with options
        createMenuBar(primaryStage);
    }

    /**
     * Resume : Function that asks for a Year
     * @return
     * @param userChoosenFile
     */
    private String askYearFile(String userChoosenFile) {


        //Gets the File that the user Choose and Shows the Years Inside It for the User to Choose it
        List<String> allYearsList = chartRacer.getAllYearsList(userChoosenFile);
        System.out.println("All Years List View Side = " + allYearsList);

        return "";
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
        }
        else {
            //Gets the File Path
            filePath = selectedFile.getAbsolutePath();
        }
        System.out.println("Choosen File = " + filePath);
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

            String userChoosenFile = askUserFile(primaryStage);

            //Method that asks User File and asks user year and Draws the Chart
            createChart(userChoosenFile, askYearFile(userChoosenFile));

        });

        //Adds Items to Menu
        menu.getItems().addAll(menuItemDraw1Year, menuItemDrawAllYears);

        //Adds Menu to Menu Bar
        menuBar.getMenus().add(menu);

        //Adds Menu Bar To Program
        this.getChildren().add(menuBar);
    }

    private void createChart(String file, String year) {

        year = "1500";

        //Gets the Data Relative to the Graphic and Draws it
        chartRacer.getDataToDrawGhraphic(chartRacer.getSpecificYearData(chartRacer.readFile(file), year));
    }

    @Override
    public void drawGraphic(List<String> specificYearData) {
        System.out.println("View Side 1500 List = " + specificYearData);

        //TODO - Make Variable Indexes so it's easier to displace items in pane

        //Sets Up Text About the Chart
        this.getChildren().add(new Text(50, 50,"Graphic that Represents the Demographic Population in Various Cities of the World"));

        //Draws City Name
        this.getChildren().add(new Text(50, 130,"City Name"));

        //Sets Up Lines to make the Graphic
        this.getChildren().add(new LineChartRacer(150, 60, 150, 200));

        //Sets Up the Bars of the Graphic
        this.getChildren().add(new RectangleChartRacer(150, 100, 150, 50));

        //Draws City Population
        this.getChildren().add(new Text(350, 130,"City Population"));
    }
}
