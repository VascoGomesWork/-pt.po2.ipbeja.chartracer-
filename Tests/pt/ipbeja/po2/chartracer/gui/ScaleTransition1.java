package pt.ipbeja.po2.chartracer.gui;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 16/05/2022
 */
public class ScaleTransition1 {

    ScaleTransition scaleTransition;
    ScaleTransition scaleTransition1 = new ScaleTransition();
    ParallelTransition parallelTransition = new ParallelTransition();
    Rectangle rectangleToAnimate;
    Rectangle oldRectangleToAnimate;
    public ScaleTransition1() {
        scaleTransition = new ScaleTransition();
    }

    public void runScaleTransition(Rectangle rectangle, int xToScale){

        Platform.runLater( () -> {

            //Set the Desired Properties Like Duration, Cycle-Count, etc for the transition
            scaleTransition.setDuration(Duration.millis(500));
            //scaleTransition.setToX(50);
            scaleTransition.setFromX(0);
            scaleTransition.setToX(xToScale);

            //Set the Target Node on Witch the Transition will be Applied.
            //Use the Following Method for this purpose
            scaleTransition.setNode(rectangle);

            //Finally, Play the Transition Using the Play Method.
            scaleTransition.play();

        });
    }

    public void testBarMovement(List<Rectangle> rectangleList, List<Integer> lastBarSizeList, int newBarSize, int counter, int monthCounter){
        //Gets old bar size so it can determine the new one
        int oldX = lastBarSizeList.get(counter);
        rectangleToAnimate = rectangleList.get(counter);
        Platform.runLater(() -> {
            //lastBarSizeList.add(newBarSize);
            scaleTransition.setDuration(Duration.millis(500));
            scaleTransition.setFromX(oldX);
            scaleTransition.setToX(newBarSize);

            //scaleTransition1.setFromY(300);
            //scaleTransition1.setToY(50);

            //scaleTransition1.setNode(rectangleToAnimate);
            scaleTransition.setNode(rectangleToAnimate);

            //Function that Displaces the Bars Through The Chart According to their population Values
            displaceBarsInChart(rectangleList, lastBarSizeList, newBarSize, counter);

            scaleTransition.play();
        });
    }

    private void displaceBarsInChart(List<Rectangle> rectangleList, List<Integer> lastBarSizeList, int newBarSize, int counter) {
        //For Loop loops through the rectangles backwards to see if there are any smaller rectangles to make a switch
        //Get a Backwards Counter
        int newCounter = rectangleList.indexOf(rectangleToAnimate);
        for (int i = 12; i < lastBarSizeList.size(); i++) {
            if(newBarSize > lastBarSizeList.get(i)){
                //TODO - Make new variable
                oldRectangleToAnimate = rectangleToAnimate;
                System.out.println(newBarSize + " É maior que " + lastBarSizeList.get(i));
                System.out.println("I = " + i);
                permutePositions(rectangleList, newCounter);
                oldRectangleToAnimate = rectangleList.get(counter - 1);
                newCounter--;
            }
        }
        System.out.println();
    }


    private void permutePositions(List<Rectangle> rectangleList, int counter){

        rectangleToAnimate.setY(rectangleToAnimate.getY() - 50);
        rectangleList.get(rectangleList.indexOf(rectangleToAnimate) - counter).setY(rectangleList.get(rectangleList.indexOf(rectangleToAnimate) - counter).getY() + 50);
    }
}
