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

    /**
     * Resume: Graphical Skins Constructor
     * @param xChartBar
     * @param yChartBar
     * @param population
     */
    public GraphicalSkins(int xChartBar, int yChartBar, int population) {
        this.xChartBar = xChartBar;
        this.yChartBar = yChartBar;
        this.population = population;
    }

    /**
     * Resume: Returns xChartBar Variable
     * @return
     */
    public int xChartBar() {
        return xChartBar;
    }

    /**
     * Resume: Returns yChartBar Variable
     * @return
     */
    public int yChartBar() {
        return yChartBar;
    }

    /**
     * Resume: Returns population Variable
     * @return
     */
    public int population() {
        return population;
    }

    /**
     * Resume: Abstract Function that Generates Skin
     * @return
     */
    public abstract List<List<Node>> generateSkin();

    /**
     * Resume : Function that Generates Random RGB Number Between 0 and 255
     * @return
     */
    public int generateRandRGBNumber(){
        return (int) ((Math.random() * (255)) + 0);
    }
}
