package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;

import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 27/05/2022
 */
public abstract class GraphicalSkins implements Skin{

    private final int xChartBar;
    private final int yChartBar;
    private final int population;

    public GraphicalSkins(int xChartBar, int yChartBar, int population) {
        this.xChartBar = xChartBar;
        this.yChartBar = yChartBar;
        this.population = population;
    }

    public int xChartBar() {
        return xChartBar;
    }

    public int yChartBar() {
        return yChartBar;
    }

    public int population() {
        return population;
    }

    public abstract List<List<Node>> generateSkin();

    /**
     * Resume : Function that Generates Random RGB Number Between 0 and 255
     * @return
     */
    public int generateRandRGBNumber(){
        return (int) ((Math.random() * (255)) + 0);
    }
}
