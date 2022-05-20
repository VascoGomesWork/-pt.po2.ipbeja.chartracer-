package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @author Vasco Gomes 19921
 * @date 16/05/2022
 */
public class NewPaneTest extends Pane {

    public NewPaneTest(int x, int y) {
        this.setLayoutX(60);
        this.setLayoutY(60);
        //this.setMaxSize(3, 3);
        /*this.setMaxHeight(y);
        this.setMaxWidth(x);*/
        //this.setStyle("-fx-background-color: black;");
    }

    public Pane addObjectsDrawingPane(Object javaFxObject){
        System.out.println("Teste");
        Platform.runLater(() -> {
        this.getChildren().add(((Node) javaFxObject));
        });
        return this;
    }
}
