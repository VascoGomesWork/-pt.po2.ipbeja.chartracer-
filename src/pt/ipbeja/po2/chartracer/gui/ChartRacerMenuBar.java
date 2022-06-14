package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Vasco Gomes 19921
 * @date 31/05/2022
 */
public class ChartRacerMenuBar extends MenuBar {

    MenuBar menuBar = new MenuBar();
    Menu menuGraphicOperations = new Menu("Graphic Operations");
    Menu menuSkins = new Menu("Skin");
    Menu menuData = new Menu("Data");
    Menu menuProgramOperations = new Menu("Program Operations");

    MenuItem menuItemDraw1Year = new MenuItem("Draw 1 Year");
    MenuItem menuItemDrawAllYears = new MenuItem("Draw All Years");
    MenuItem menuItemClearAll = new MenuItem("Clear All");
    MenuItem menuChooseTextColor = new MenuItem("Choose Text Color");
    MenuItem menuItemExit = new MenuItem("Exit");

    CheckMenuItem menuGraphicLinesSkin = new CheckMenuItem("Line Bar Chart Skin");
    CheckMenuItem menuGraphicColorsSkin = new CheckMenuItem("Colored Bar Chart Skin");
    CheckMenuItem menuGenerateFile = new CheckMenuItem("Generate File");
    CheckMenuItem menuDarkMode = new CheckMenuItem("Dark Mode");

    public ChartRacerMenuBar() {

    }

    /**
     * Resume: Function that Returns Menu Bar
     * @return: MenuBar
     */
    public MenuBar getMenuBar() {
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuBar.html
        //https://stackoverflow.com/questions/10315774/javafx-2-0-activating-a-menu-like-a-menuitem

        //Adds Items to Graphic Operations Menu
        menuGraphicOperations.getItems().addAll(menuItemDraw1Year, menuItemDrawAllYears, menuItemClearAll);
        //Adds Items to Skin Menu
        menuSkins.getItems().addAll(menuGraphicLinesSkin, menuGraphicColorsSkin);
        //Adds Items to Data Menu
        menuData.getItems().add(menuGenerateFile);
        //Adds Items to Program Operation Menu
        menuProgramOperations.getItems().addAll(menuDarkMode, menuChooseTextColor, menuItemExit);
        //Adds Menu to Menu Bar
        menuBar.getMenus().addAll(menuGraphicOperations, menuSkins, menuData ,menuProgramOperations);

        return menuBar;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
