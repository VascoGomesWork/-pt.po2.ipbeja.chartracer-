package pt.ipbeja.po2.chartracer.gui;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.ChartRacer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 16/05/2022
 */
public class AnimationTestStart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Creating a Target Node
        Rectangle rectangle = new Rectangle(50, 50, 50, 50);
        rectangle.setFill(Color.RED);

        Rectangle rectangle2 = new Rectangle(50, 100, 50, 50);
        rectangle2.setFill(Color.GREEN);

        Rectangle rectangle3 = new Rectangle(50, 150, 50, 50);
        rectangle3.setFill(Color.GREENYELLOW);

        Rectangle rectangle4 = new Rectangle(50, 200, 50, 50);
        rectangle4.setFill(Color.YELLOW);

        Rectangle rectangle5 = new Rectangle(50, 250, 50, 50);
        rectangle5.setFill(Color.DARKGRAY);

        Rectangle rectangle6 = new Rectangle(50, 300, 50, 50);
        rectangle6.setFill(Color.BLUE);

        Rectangle rectangle7 = new Rectangle(50, 350, 50, 50);
        rectangle7.setFill(Color.BLACK);

        Rectangle rectangle8 = new Rectangle(50, 400, 50, 50);
        rectangle8.setFill(Color.BLUEVIOLET);

        Rectangle rectangle9 = new Rectangle(50, 450, 50, 50);
        rectangle9.setFill(Color.BROWN);

        Rectangle rectangle10 = new Rectangle(50, 500, 50, 50);
        rectangle10.setFill(Color.LAVENDER);

        Rectangle rectangle11 = new Rectangle(50, 550, 50, 50);
        rectangle11.setFill(Color.AZURE);

        Rectangle rectangle12 = new Rectangle(50, 600, 50, 50);
        rectangle12.setFill(Color.DARKSALMON);

        List<Rectangle> rectangleList = new ArrayList<>();
        rectangleList.add(rectangle);
        rectangleList.add(rectangle2);
        rectangleList.add(rectangle3);
        rectangleList.add(rectangle4);
        rectangleList.add(rectangle5);
        rectangleList.add(rectangle6);
        rectangleList.add(rectangle7);
        rectangleList.add(rectangle8);
        rectangleList.add(rectangle9);
        rectangleList.add(rectangle10);
        rectangleList.add(rectangle11);
        rectangleList.add(rectangle12);

        ChartRacer chartRacer = new ChartRacer();

        String userFile = "src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt";
        //TODO - Make for loop too loop through years
        List<List<String>> allYearsList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(String.valueOf(chartRacer.getAllYearsList(userFile).size())); i++) {
            allYearsList.add(chartRacer.orderByPopulation(chartRacer.readFile(userFile), Integer.parseInt(chartRacer.getAllYearsList(userFile).get(i))));
        }


        System.out.println("All Year List View Side = " + allYearsList);

        //https://examples.javacodegeeks.com/desktop-java/javafx/javafx-transition-example/
        //Instantiate the Respective Transition Class
        ScaleTransition1 scaleTransition1 = new ScaleTransition1();
        String yearTest = chartRacer.getYear(allYearsList.get(0).get(0)+ "");
        Pane pane = new Pane();
        Rectangle rectanglePane = new Rectangle(0, 0, 70, 700);
        TextChartRacer textChartRacer = new TextChartRacer(300, 50, yearTest);
        List<Integer> lastBarSizeList = new ArrayList<>();


        pane.getChildren().addAll(rectangle, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7, rectangle8, rectangle9, rectangle10, rectangle11, rectangle12 , rectanglePane, textChartRacer);

        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

        //TODO - Make Ratio and make bar width grow when changing spots
        //Counter is used to put the bars in it's correct order
        //Creating a new Thread and executing code inside
        new Thread( () -> {

            int counter = 0;
            for (int i = 0; i < allYearsList.size(); i++) {
                for (int monthCounter = 11; monthCounter >= 0; monthCounter--) {
                    String year = chartRacer.getYear(allYearsList.get(i).get(monthCounter));
                    //yearTest = year;
                    chartRacer.getCity(allYearsList.get(i).get(monthCounter));
                    chartRacer.getCountry(allYearsList.get(i).get(monthCounter));

                    String population = chartRacer.getPopulationByCity(allYearsList.get(i).get(monthCounter));
                    //Puts inside a List Every X

                    if(i == 0){
                        lastBarSizeList.add(getBarRatio(Integer.parseInt(population), counter));
                        System.out.println("Year = " + year + " Population  = " + population + " Counter = " + counter + " Bar Ratio = " + lastBarSizeList.get(counter));
                        scaleTransition1.runScaleTransition(rectangleList.get(counter), lastBarSizeList.get(counter));
                    }else{
                        //Test
                        int newBarSize = 65;
                        if(counter == 5/*5*/){
                            newBarSize = 67;
                        }

                        if(counter == 7/*5*/){
                            newBarSize = 68;
                        }

                        if(counter == 8/*5*/){
                            newBarSize = 66;
                        }

                        if(counter == 11/*5*/){
                            newBarSize = 69;
                        }

                        lastBarSizeList.add(newBarSize);
                        //scaleTransition1.testBarMovement(rectangleList.get(counter), oldXList.get(0), newXToScale, oldXList);
                        scaleTransition1.testBarMovement(rectangleList, lastBarSizeList, newBarSize, counter, monthCounter);
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter++;
                }

                System.out.println("Counter = " + counter);
                counter = 0;
            }

        }).start();

        //Get Background Color of Pane and Apply it to rectanglePane
        //rectanglePane.setStyle(pane.getStyle());

    }

    private int getBarRatio(int population, int counter) {
        return population / (population * (counter + 1) / 60);
    }
}
