package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.layout.Pane;

/**
 * @author Vasco Gomes 19921
 * @date 16/05/2022
 */
public class NewPaneTest extends Pane {

    public NewPaneTest(int x, int y) {
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.setMaxSize(3, 3);
        /*this.setMaxHeight(y);
        this.setMaxWidth(x);*/
        this.setStyle("-fx-background-color: black;");
    }
}
