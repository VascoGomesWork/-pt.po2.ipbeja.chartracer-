package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Vasco Gomes 19921
 * @date 09/05/2022
 */
public class ChartRacerModel {

    private int cityEndIndex;
    private int countryEndIndex;
    private int populationEndIndex;
    private static int NUM_BARS = 12;
    private boolean isThreadAlive = false;
    private View view;
    private List<String> yearsBeforeList = new ArrayList<>();

    /**
     * Resume : Constructor that Sets Up the View
     * @param view
     */
    public ChartRacerModel(View view) {
        this.view = view;
    }

    /**
     * Resume : Dummy Constructor for Tests Purposes
     */
    public ChartRacerModel() {}

    /**
     * Resume : Method to Read Cities Data from the File
     * @param fileName
     * @return: List of Strings From a File
     */
    public List<String> readFile(String fileName) {
        try {
            return removeUnwantedDataFromList(Files.readAllLines(Paths.get(fileName)));
        } catch (Exception e) {}
        return null;
    }

    /**
     * Resume : Function that makes a newList
     * @param citiesList
     * @return: Returns New List Without Unwanted Data
     */
    public List<String> removeUnwantedDataFromList(List<String> citiesList) {
        //Removes the first lines until the first number 12 appears in the file
        if(citiesList.contains("12")) {
            citiesList.subList(0, citiesList.indexOf("12")).clear();
            //Removes all numbers 12 and spaces
            citiesList.removeAll(Collections.singleton("12"));
            citiesList.removeAll(Collections.singleton(" "));
        }
        return citiesList;
    }

    /**
     * Resume : Orders List By Population Number with Comparable Interface implemented in OrderCitiesData Class
     * @param fileData
     * @param year
     * @return: Ordered List By Population Number
     */
    public List<String> orderByPopulation(List<String> fileData, int year) {
        //https://www.youtube.com/watch?v=wboqZ2dPDtQ
        CitiesData citiesData;
        List<String> specificYearUnorderedData = getSpecificYearData(fileData, year+"");
        List<CitiesData> specificYearOrderedData = new ArrayList<>();
        List<String> finalSpecificYearOrderedData = new ArrayList<>();

        for (int i = 0; i < specificYearUnorderedData.size(); i++) {
            if(specificYearUnorderedData.get(i).contains(",")) {
                String data = specificYearUnorderedData.get(i);
                citiesData = new CitiesData(getYear(data), getCity(data), getCountry(data), getPopulationByCity(data), getRegion(data));
                //Adds to the ordered List what comes from Comparable
                specificYearOrderedData.add(citiesData);
            }
        }

        //Sorts the List by Population Number
        Collections.sort(specificYearOrderedData);

        //Get String inside of an ordered finalList
        for (int i = 0; i < specificYearOrderedData.size(); i++) {
            finalSpecificYearOrderedData.add(specificYearOrderedData.get(i).convertToString(specificYearOrderedData.get(i)));
        }
        return finalSpecificYearOrderedData;
    }

    /**
     * Resume : Function that Gets the Year from List Item
     * @param dataString
     * @return: Gets Year From List Item
     */
    public String getYear(String dataString) {
        return dataString.substring(0, dataString.indexOf(','));
    }

    /**
     * Resume : Function that Gets the City from List Item
     * @param dataString
     * @return: Gets City From List Item
     */
    public String getCity(String dataString) {
        int beginIndex = dataString.indexOf(",");
        int endIndex = dataString.indexOf(",", beginIndex + 1);
        this.cityEndIndex = endIndex;
        return dataString.substring(beginIndex, endIndex).replace(",", "");
    }

    /**
     * Resume : Function that Gets the Country from List Item
     * @param dataString
     * @return: Gets Country From List Item
     */
    public String getCountry(String dataString) {
        //Uses globally defined variable cityEndIndex saved before as starting point
        int beginIndex = dataString.indexOf(",", this.cityEndIndex);
        int endIndex = dataString.indexOf(",", beginIndex + 1);
        this.countryEndIndex = endIndex;
        return dataString.substring(beginIndex, endIndex).replace(",", "");
    }

    /**
     * Resume : Function that Gets the Population from List Item
     * @param dataString
     * @return: Gets Population From List Item
     */
    public String getPopulationByCity(String dataString) {
        //Uses globally defined variable countryEndIndex saved before as starting point
        int beginIndex = dataString.indexOf(",", this.countryEndIndex);
        int endIndex = dataString.lastIndexOf(',');
        this.populationEndIndex = endIndex;
        return dataString.substring(beginIndex, endIndex).replace(",", "");
    }

    /**
     * Resume : Function that Gets the Region from List Item
     * @param dataString
     * @return: Gets Region From List Item
     */
    public String getRegion(String dataString) {
        //Uses globally defined variable populationEndIndex saved before as starting point
        int beginIndex = dataString.indexOf(",", this.populationEndIndex);
        int endIndex = dataString.length();
        return dataString.substring(beginIndex, endIndex).replace(",", "");
    }

    /**
     * Resume : Gets The Data relative to the Specified Year
     * @param fileData
     * @param year
     * @return: Data Specific to 1 Year
     */
    public List<String> getSpecificYearData(List<String> fileData, String year) {

        List<String> specificYearList = new ArrayList<>();

        for (int i = 0; i < fileData.size(); i++) {
            //Gets the Year of every list entrance from the beginning to the first ',' and checks if it's equal to the year inserted
            try{
                if(fileData.get(i).substring(0, fileData.get(i).indexOf(',')).equals(year)){
                    specificYearList.add(fileData.get(i));
                }
            }
            catch (Exception e){
            }
        }
        return specificYearList;
    }

    /**
     * Resume : Method that Sends Data to the View
     * @param specificYearData
     */
    public void getDataToDrawGraphic(List<String> specificYearData) {
        this.view.drawGraphic(specificYearData);
    }

    /**
     * Resume : Method that returns a List with all the Years on a File
     * @param userChosenFile
     * @return: List With All Years
     */
    public List<String> getAllYearsList(String userChosenFile) {

        List<String> userReadFile = readFile(userChosenFile);
        List<String> allYearsList = new ArrayList<>();

        if(userReadFile != null) {
            for (int i = 0; i < userReadFile.size(); i++) {
                //Gets all the Years in File Choosen By The User and Puts it in all Year List
                //Checks if String Length is Greater Than 0
                if (userReadFile.get(i).length() > 0) {
                    allYearsList.add(getYear(userReadFile.get(i)));
                }
            }
        }
        //Calls Method to Remove Repeated Elements from List with TreeSet
        return removeRepeatedElements(allYearsList);
    }

    /**
     * Resume : Remove Repeated Elements from List with TreeSet
     * @param allYearsList
     * @return: List Without Repeated Elements
     */
    private List<String> removeRepeatedElements(List<String> allYearsList) {

        //Delete Repeated Years with TreeSet
        TreeSet<String> allYearsTreeSet = new TreeSet<>(allYearsList);
        //Clears the List
        allYearsList.clear();
        //Adds All Elements of TreeSet on the List Again
        allYearsList.addAll(allYearsTreeSet);

        return allYearsList;
    }

    /**
     * Resume : Function that gets the quantity of years in a list using TreeSet to remove duplicates
     * @param dataList
     * @return: Quantity of Years In List
     */
    public int getQtyYearsInList(List<String> dataList) {
        List<String> yearsList = new ArrayList<>();
        if(dataList != null) {
            for (int i = 0; i < dataList.size(); i++) {
                //Check if String not Empty
                if (dataList.get(i).length() > 0) {
                    yearsList.add(getYear(dataList.get(i)));
                }
            }
            return new TreeSet<>(yearsList).size();
        }
        return 0;
    }

    private Thread thread;
    /**
     * Resume : Function that gets the data to animate and draw all the graphics and uses a new Thread to JavaFx Thread be available to update View
     * @param yearDataChartRacer
     */
    public void getDataDrawAllGraphics(List<List<String>> yearDataChartRacer) {
        //Chanel Teams PO2 2019-2020 Video 4 about Threads of Professor João Paulo Barros
        this.thread = new Thread( () -> {
            int counter = 0;
            List<String> yearBeforeList = new ArrayList<>();
            for (int i = 0; i < yearDataChartRacer.size(); i++) {
                if(counter < NUM_BARS) {

                    this.isThreadAlive = true;
                    this.view.drawAllGraphics(orderByPopulation(yearDataChartRacer.get(i), Integer.parseInt(getYear(yearDataChartRacer.get(i).get(counter)))), this.thread);
                    this.isThreadAlive = false;
                } else if(counter == NUM_BARS){
                    counter = 0;
                }
                counter++;
            }
        });
        this.thread.start();
    }

    /**
     * Resume: Function that returns a boolean accordingly with threadStatus
     * @return isThreadAlive
     */
    public boolean getThreadStatus(){
        return this.isThreadAlive;
    }

    /**
     * Resume: Function that Sets Thread Status
     * @param b
     */
    public void setThreadStatus(boolean b) {
        this.isThreadAlive = b;
    }

    /**
     * Resume : Function that Generates a Statistic File
     * @param dataFile
     */
    public void generateStatisticFile(String dataFile) {
        List<String> stringDataList = readFile(dataFile);
        List<String> dataToFileList = new ArrayList<>();
        //Adds Data To List that Will be Written in the File
        dataToFileList.add("Number of Data Sets in File: " + getQtyYearsInList(stringDataList));
        dataToFileList.add("First Date: " + getYear(stringDataList.get(0)));
        dataToFileList.add("Last Date: " + getYear(stringDataList.get(stringDataList.size() - 1)));
        dataToFileList.add("Average Number of Lines in Each Data Set: " + getDataSetAverageLines(stringDataList));
        dataToFileList.add("Number of Columns in Each Data Set: " + getColumnsQty(stringDataList));
        dataToFileList.add("Maximum Value Considering All Data Sets: " + maximumPopulationValue(stringDataList));
        dataToFileList.add("Minimum Value Considering All Data Sets: " + minimumPopulationValue(stringDataList));
        //Write Data in File
        writeDataFile("src/pt/ipbeja/po2/chartracer/model/StatisticData.txt", dataToFileList);
    }

    /**
     * Resume : Function that Gets the Average Number of Lines
     * @param stringDataList
     * @return: averageNumLines
     */
    private int getDataSetAverageLines(List<String> stringDataList) {
        int counter = 0;
        for (String data : stringDataList) {
            if(data.length() > 0 && getYear(data).equals(getYear(stringDataList.get(0)))){
                counter++;
            } else return counter;
        }
        return counter;
    }

    /**
     * Resume : Function that gets the Column Quantity
     * @param stringDataList
     * @return: columnQty
     */
    private int getColumnsQty(List<String> stringDataList) {
        int counter = 0;
        for (int i = 0; i < stringDataList.get(0).length(); i++) {
            if(stringDataList.get(0).charAt(i) == ',') counter++;
        }
        //Returns Number of ',' + 1
        return counter + 1;
    }

    /**
     * Resume : Function that Gets the Max Population Number
     * @param stringDataList
     * @return: maxValue
     */
    private int maximumPopulationValue(List<String> stringDataList) {
        int maxValue = 0;
        for (int i = 0; i < stringDataList.size(); i++) {
            if(stringDataList.get(i).length() > 0) {
                getYear(stringDataList.get(i));
                getCity(stringDataList.get(i));
                getCountry(stringDataList.get(i));
                if (Integer.parseInt(getPopulationByCity(stringDataList.get(i))) > maxValue) {
                    maxValue = Integer.parseInt(getPopulationByCity(stringDataList.get(i)));
                }
            }
        }
        return maxValue;
    }

    /**
     * Resume : Function that Gets the Min Population Number
     * @param stringDataList
     * @return: minValue
     */
    private int minimumPopulationValue(List<String> stringDataList) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < stringDataList.size(); i++) {
            if(stringDataList.get(i).length() > 0) {
                getYear(stringDataList.get(i));
                getCity(stringDataList.get(i));
                getCountry(stringDataList.get(i));
                if (Integer.parseInt(getPopulationByCity(stringDataList.get(i))) < minValue) {
                    minValue = Integer.parseInt(getPopulationByCity(stringDataList.get(i)));
                }
            }
        }
        return minValue;
    }

    /**
     * Resume : Function that Writes Data into File
     * @param filePath
     * @param dataList
     */
    public void writeDataFile(String filePath, List dataList) {
        try {
            Files.write(Paths.get(filePath), dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
