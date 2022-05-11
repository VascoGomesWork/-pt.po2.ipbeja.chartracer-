package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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

    public ChartRacerBoard(int minWidth) {

        this.minWidth = minWidth;
        //TODO - Make method with a Menu bar with options
        createMenuBar(minWidth);

        //TODO - Make method to ask the File
        String fileName = askUserFile();

        //TODO - Make method to ask the year
        String year = askYearFile();

        //Method that creates the chart
        createChart();
    }

    /**
     * Resume : Function that asks for a Year
     * @return
     */
    private String askYearFile() {
        return "";
    }

    /**
     * Resume : Function that asks for the file to Read
     * @return
     */
    private String askUserFile() {
        //https://openjfx.io/javadoc/17/javafx.graphics/javafx/stage/FileChooser.html
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text Files");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        //File selectedFile = fileChooser.showOpenDialog(super.getScene().getWindow());
        String filePath = "";
        //If File is Null shows an error
        /*if(selectedFile == null){
            System.out.println("ERROR : Did Not Choose any File!!");
        }
        else {
            //Gets the File Path
            filePath = selectedFile.getAbsolutePath();
        }*/
        System.out.println("Choosen File = " + filePath);
        return filePath;
    }

    private void createMenuBar(int barWidth) {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Graphic Operations");
        //this.addMenuItem(menu, "Teste");
        //menuBar.setWidth();
        MenuItem menuItem = new MenuItem("Draw 1 Year");
    }

    private void createChart() {

        String fileName = "src/pt/ipbeja/po2/chartracer/model/cities.txt";

        chartRacer.getDataToDrawGhraphic(chartRacer.getSpecificYearData(chartRacer.readFile(fileName), 1500+""));
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
