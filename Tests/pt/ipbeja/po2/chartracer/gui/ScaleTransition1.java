package pt.ipbeja.po2.chartracer.gui;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Vasco Gomes 19921
 * @date 16/05/2022
 */
public class ScaleTransition1 {

    public ScaleTransition1() {

    }

    public void runScaleTransition(Rectangle rectangle, int xToScale){

        Platform.runLater( () -> {
            ScaleTransition scaleTransition = new ScaleTransition();

            //Set the Desired Properties Like Duration, Cycle-Count, etc for the transition
            scaleTransition.setDuration(Duration.seconds(3));
            //scaleTransition.setToX(50);
            scaleTransition.setFromX(0);
            scaleTransition.setToX(xToScale);
            //translateTransition.set

            //translateTransition.setToY(500);
            //scaleTransition.setAutoReverse(true);
            //scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);

            //Set the Target Node on Witch the Transition will be Applied.
            //Use the Following Method for this purpose
            scaleTransition.setNode(rectangle);

            //Finally, Play the Transition Using the Play Method.
            scaleTransition.play();
        });
    }
}
