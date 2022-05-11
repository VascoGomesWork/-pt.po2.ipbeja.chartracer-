package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vasco Gomes 19921
 * @date 09/05/2022
 */
class ChartRacerTest {

    ChartRacer chartRacer;
    String fileName = "src/pt/ipbeja/po2/chartracer/model/cities.txt";

    @BeforeEach
    void setUp() {
        this.chartRacer = new ChartRacer();
    }

    @Test
    void teste1() {
        //Ler um ficheiro de dados e verificar que está bem lido (os dados lidos são os esperados);
        try {

            List<String> fileData = Files.readAllLines(Paths.get(fileName));
            assertEquals(chartRacer.removeUnwantedDataFromList(fileData), chartRacer.readFile(fileName));

            /*for (int i = 0; i < fileData.size(); i++) {
                System.out.println(fileData.get(i));
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void teste2() {

        //Interface Comparable <T>
        List<String> fileData = chartRacer.readFile(fileName);

        //Gets the array of the Year 1500
        // Todo - Order DataYearList by number of people in city
        int year = 1500;
        List unorderedDataYearList = chartRacer.getSpecificYearData(fileData, year+"");
        //System.out.println("Data Year = " + unorderedDataYearList);
        List orderedDataYearList = null;
        try {
            //Reads in 1500
            orderedDataYearList = chartRacer.orderByPopulation(Files.readAllLines(Paths.get("src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt")), year);

            System.out.println("Ordered List By Habitants Number in 1500 = " + orderedDataYearList);
            //check if array list is ordered in 1500 with orderDa
            //Orders the data using Comparable Interface
            assertEquals(orderedDataYearList, chartRacer.orderByPopulation(fileData, year));

            //check if array list is ordered in 2018
            //Orders the data using Comparable Interface.
            //Reads in 2018
            year = 2018;
            orderedDataYearList = chartRacer.orderByPopulation(Files.readAllLines(Paths.get("src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt")), year);
            System.out.println("Ordered List By Habitants Number in 2018 = " + orderedDataYearList);
            unorderedDataYearList = chartRacer.getSpecificYearData(fileData, year+"");
            assertEquals(orderedDataYearList, chartRacer.orderByPopulation(fileData, year));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}