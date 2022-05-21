package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
        HBox hBox1 = new HBox();
        Rectangle rectangle = new Rectangle(50, 50, 50, 50);
        rectangle.setFill(Color.RED);
        hBox1.getChildren().add(rectangle);
        hBox1.setLayoutX(50);
        hBox1.setLayoutY(50);

        HBox hBox2 = new HBox();
        Rectangle rectangle2 = new Rectangle(50, 100, 50, 50);
        rectangle2.setFill(Color.GREEN);
        hBox2.getChildren().add(rectangle2);
        hBox2.setLayoutX(50);
        hBox2.setLayoutY(100);

        HBox hBox3 = new HBox();
        Rectangle rectangle3 = new Rectangle(50, 150, 50, 50);
        rectangle3.setFill(Color.GREENYELLOW);
        hBox3.getChildren().add(rectangle3);
        hBox3.setLayoutX(50);
        hBox3.setLayoutY(150);

        HBox hBox4 = new HBox();
        Rectangle rectangle4 = new Rectangle(50, 200, 50, 50);
        rectangle4.setFill(Color.YELLOW);
        hBox4.getChildren().add(rectangle4);
        hBox4.setLayoutX(50);
        hBox4.setLayoutY(200);

        HBox hBox5 = new HBox();
        Rectangle rectangle5 = new Rectangle(50, 250, 50, 50);
        rectangle5.setFill(Color.DARKGRAY);
        hBox5.getChildren().add(rectangle5);
        hBox5.setLayoutX(50);
        hBox5.setLayoutY(250);

        HBox hBox6 = new HBox();
        Rectangle rectangle6 = new Rectangle(50, 300, 50, 50);
        rectangle6.setFill(Color.BLUE);
        hBox6.getChildren().add(rectangle6);
        hBox6.setLayoutX(50);
        hBox6.setLayoutY(300);

        HBox hBox7 = new HBox();
        Rectangle rectangle7 = new Rectangle(50, 350, 50, 50);
        rectangle7.setFill(Color.BLACK);
        hBox7.getChildren().add(rectangle7);
        hBox7.setLayoutX(50);
        hBox7.setLayoutX(350);

        HBox hBox8 = new HBox();
        Rectangle rectangle8 = new Rectangle(50, 400, 50, 50);
        rectangle8.setFill(Color.BLUEVIOLET);
        hBox8.getChildren().add(rectangle8);
        hBox8.setLayoutX(50);
        hBox8.setLayoutY(400);

        HBox hBox9 = new HBox();
        Rectangle rectangle9 = new Rectangle(50, 450, 50, 50);
        rectangle9.setFill(Color.BROWN);
        hBox9.getChildren().add(rectangle9);
        hBox9.setLayoutX(50);
        hBox9.setLayoutY(450);

        HBox hBox10 = new HBox();
        Rectangle rectangle10 = new Rectangle(50, 500, 50, 50);
        rectangle10.setFill(Color.LAVENDER);
        hBox10.getChildren().add(rectangle10);
        hBox10.setLayoutX(50);
        hBox10.setLayoutY(500);

        HBox hBox11 = new HBox();
        Rectangle rectangle11 = new Rectangle(50, 550, 50, 50);
        rectangle11.setFill(Color.MIDNIGHTBLUE);
        hBox11.getChildren().add(rectangle11);
        hBox11.setLayoutX(50);
        hBox11.setLayoutY(550);

        HBox hBox12 = new HBox();
        Rectangle rectangle12 = new Rectangle(50, 600, 50, 50);
        rectangle12.setFill(Color.DARKSALMON);
        hBox12.getChildren().add(rectangle12);
        hBox12.setLayoutX(50);
        hBox12.setLayoutY(600);

        /*List<HBox> hBoxRectangleList = new ArrayList<>();
        hBoxRectangleList.add(hBox1);
        hBoxRectangleList.add(hBox2);
        hBoxRectangleList.add(hBox3);
        hBoxRectangleList.add(hBox4);
        hBoxRectangleList.add(hBox5);
        hBoxRectangleList.add(hBox6);
        hBoxRectangleList.add(hBox7);
        hBoxRectangleList.add(hBox8);
        hBoxRectangleList.add(hBox9);
        hBoxRectangleList.add(hBox10);
        hBoxRectangleList.add(hBox11);
        hBoxRectangleList.add(hBox12);*/

        List<Rectangle> hBoxRectangleList = new ArrayList<>();
        hBoxRectangleList.add(rectangle);
        hBoxRectangleList.add(rectangle2);
        hBoxRectangleList.add(rectangle3);
        hBoxRectangleList.add(rectangle4);
        hBoxRectangleList.add(rectangle5);
        hBoxRectangleList.add(rectangle6);
        hBoxRectangleList.add(rectangle7);
        hBoxRectangleList.add(rectangle8);
        hBoxRectangleList.add(rectangle9);
        hBoxRectangleList.add(rectangle10);
        hBoxRectangleList.add(rectangle11);
        hBoxRectangleList.add(rectangle12);

        ChartRacer chartRacer = new ChartRacer();

        String userFile = "src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt";
        //TODO - Make for loop too loop through years
        //TODO Chamar Função Anterior
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
        TextChartRacer textChartRacer = new TextChartRacer(50, 50, yearTest);
        List<Integer> lastBarSizeList = new ArrayList<>();


        pane.getChildren().addAll(rectangle, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7, rectangle8, rectangle9, rectangle10, rectangle11, rectangle12 , rectanglePane, textChartRacer);

        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();

        //TODO - Make Ratio and make bar width grow when changing spots
        //TODo
        //Counter is used to put the bars in it's correct order
        //Creating a new Thread and executing code inside
        new Thread( () -> {

            int counter = 0;
            for (int i = 0; i < allYearsList.size(); i++) {
                for (int monthCounter = 0; monthCounter < 12; monthCounter++) {
                    String year = chartRacer.getYear(allYearsList.get(i).get(monthCounter));
                    //yearTest = year;
                    chartRacer.getCity(allYearsList.get(i).get(monthCounter));
                    chartRacer.getCountry(allYearsList.get(i).get(monthCounter));

                    String population = chartRacer.getPopulationByCity(allYearsList.get(i).get(monthCounter));
                    //Puts inside a List Every X
                    getBarRatio(Integer.parseInt(population), i, monthCounter, primaryStage);

                    /*if(i == 0){
                        lastBarSizeList.add(getBarRatio(Integer.parseInt(population), counter));
                        System.out.println("Year = " + year + " Population  = " + population + " Counter = " + counter + " Bar Ratio = " + lastBarSizeList.get(counter));
                        scaleTransition1.runScaleTransition(rectangleList.get(counter), lastBarSizeList.get(counter));
                    }else{*/
                        //Test
                        int newBarSize = 65;
                        if(i == 1){
                            if(counter == 5){
                                newBarSize = 67;
                            }

                            if(counter == 6){
                                newBarSize = 66;
                            }

                            if(counter == 7){
                                newBarSize = 68;
                            }
                        }

                        /*if(i == 0){
                            lastBarSizeList.add(getBarRatio(Integer.parseInt(population), i));
                        } else {
                            lastBarSizeList.add(newBarSize);
                        }*/
                        //int newBarSize = getBarRatio(Integer.parseInt(population), i);
                        lastBarSizeList.add(newBarSize);
                        //scaleTransition1.testBarMovement(rectangleList.get(counter), oldXList.get(0), newXToScale, oldXList);
                        scaleTransition1.runTransition(hBoxRectangleList, lastBarSizeList, newBarSize, counter, i);
                    //}

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
    }

    //TODO - Fix Bar Ratio
    private int getBarRatio(int population, int i, int monthCounter, Stage primaryStage) {
        if(i > 0){
            System.out.println("IF Counter = " + i + " Width = " + population / (primaryStage.getWidth() * monthCounter));
            return population / (100 * i);
        }
        System.out.println("Counter = " + i + " Width = " + primaryStage.getWidth() / population);
        return population / (50);
    }
}
