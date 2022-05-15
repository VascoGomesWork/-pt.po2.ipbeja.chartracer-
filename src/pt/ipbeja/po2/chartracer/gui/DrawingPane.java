package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @author Vasco Gomes 19921
 * @date 15/05/2022
 */
public class DrawingPane extends Pane {

    public DrawingPane(int xAxis, int yAxis) {
        this.setLayoutX(xAxis);
        this.setLayoutY(yAxis);
    }

    public void addObjectsDrawingPane(Object javaFxObject){
        this.getChildren().add(((Node) javaFxObject));
    }

    public void clear() {
        this.getChildren().clear();
    }

    public void drawGraphic() {

    }
}
