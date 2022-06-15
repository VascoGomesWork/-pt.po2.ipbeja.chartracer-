package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vasco Gomes 19921
 * @date 09/05/2022
 */
class ChartRacerModelTest {

    ChartRacerModel chartRacerModel;
    String fileName = "src/pt/ipbeja/po2/chartracer/model/cities.txt";

    @BeforeEach
    void setUp() {
        this.chartRacerModel = new ChartRacerModel();
    }

    @Test
    void teste1() {
        //Ler um ficheiro de dados e verificar que está bem lido (os dados lidos são os esperados);
        try {

            List<String> fileData = Files.readAllLines(Paths.get(fileName));
            assertEquals(chartRacerModel.removeUnwantedDataFromList(fileData), chartRacerModel.readFile(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void teste2() {

        //Interface Comparable <T>
        List<String> fileData = chartRacerModel.readFile(fileName);

        //Gets the array of the Year 1500
        //Order DataYearList by number of people in city
        int year = 1500;
        //List unorderedDataYearList = chartRacer.getSpecificYearData(fileData, year+"");
        //System.out.println("Data Year = " + unorderedDataYearList);
        List orderedDataYearList = null;
        try {
            //Reads in 1500
            orderedDataYearList = chartRacerModel.orderByPopulation(Files.readAllLines(Paths.get("src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt")), year);

            System.out.println("Ordered List By Habitants Number in 1500 = " + orderedDataYearList);
            //check if array list is ordered in 1500 with orderDa
            //Orders the data using Comparable Interface
            assertEquals(orderedDataYearList, chartRacerModel.orderByPopulation(fileData, year));

            //check if array list is ordered in 2018
            //Orders the data using Comparable Interface.
            //Reads in 2018
            year = 2018;
            orderedDataYearList = chartRacerModel.orderByPopulation(Files.readAllLines(Paths.get("src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt")), year);
            System.out.println("Ordered List By Habitants Number in 2018 = " + orderedDataYearList);
            //unorderedDataYearList = chartRacer.getSpecificYearData(fileData, year+"");
            assertEquals(orderedDataYearList, chartRacerModel.orderByPopulation(fileData, year));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void teste3() {
        List ordered5ElementsDataYearList = null;
        List<String> checkOrder5FirstFromFile = null;

        try {
            int year = 1500;
            //Reads in 1500
            ordered5ElementsDataYearList = chartRacerModel.orderByPopulation(Files.readAllLines(Paths.get("src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt")), year).subList(0, 5);
            System.out.println("5 Items from 1500 Ordered Data year List = " + ordered5ElementsDataYearList);

            //Puts orderDataYearList inside a file
            chartRacerModel.writeDataFile("src/pt/ipbeja/po2/chartracer/model/OrderedDataYear5.txt", ordered5ElementsDataYearList);

            //Extracts the information from the file
            checkOrder5FirstFromFile = chartRacerModel.readFile("src/pt/ipbeja/po2/chartracer/model/OrderedDataYear5.txt");
            assertEquals(ordered5ElementsDataYearList, checkOrder5FirstFromFile);

            //Changes the Year to 2018

            year = 2018;
            //Reads in 2018
            ordered5ElementsDataYearList = chartRacerModel.orderByPopulation(Files.readAllLines(Paths.get("src/pt/ipbeja/po2/chartracer/model/OrderedCitiesSample.txt")), year).subList(0, 5);
            System.out.println("5 Items from 2018 Ordered Data year List = " + ordered5ElementsDataYearList);

            //Puts orderDataYearList inside a file
            Files.write(Paths.get("src/pt/ipbeja/po2/chartracer/model/OrderedDataYear5.txt"), ordered5ElementsDataYearList);

            //Extracts the information from the file
            checkOrder5FirstFromFile = chartRacerModel.readFile("src/pt/ipbeja/po2/chartracer/model/OrderedDataYear5.txt");
            assertEquals(ordered5ElementsDataYearList, checkOrder5FirstFromFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}