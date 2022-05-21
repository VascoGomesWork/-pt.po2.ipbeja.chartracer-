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
public class ChartRacer {

    String dataString = "";
    private int cityEndIndex;
    private int countryEndIndex;
    private int populationEndIndex;
    View view;

    /**
     * Resume : Constructor that Sets Up the View
     * @param view
     */
    public ChartRacer(View view) {
        this.view = view;
    }

    /**
     * Resume : Dummy Constructor for Tests Purposes
     */
    public ChartRacer() {}

    /**
     * Resume : Method to Read Cities Data from the File
     * @param fileName
     * @return
     */
    public List<String> readFile(String fileName) {
        //TODO - Check if the File has length > 0 and the file format of year,city,country,population,region
        try {
                return removeUnwantedDataFromList(Files.readAllLines(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Resume : Function that makes a newList
     * @param citiesList
     * @return
     */
    public List<String> removeUnwantedDataFromList(List<String> citiesList) {
        //Removes the first lines until the first number 12 appears in the file
        if(citiesList.contains("12")) {
            citiesList.subList(0, citiesList.indexOf("12")).clear();
            //Removes all numbers 12 and spaces
            citiesList.removeAll(Collections.singleton("12"));
            citiesList.removeAll(Collections.singleton(" "));
            //Removes all lines with length is 0
            //citiesList.subList(0, citiesList.size()).clear();
            //citiesList.removeAll(citiesList.get(c).length() == 0);
        }
        return citiesList;
    }

    /**
     * Resume : Orders List By Population Number with Comparable Interface implemented in OrderCitiesData Class
     * @param fileData
     * @param year
     * @return
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
                System.out.println();
                //Adds to the ordered List what comes from Comparable
                specificYearOrderedData.add(citiesData);
            }
        }

        //Sorts the List by Population Number
        Collections.sort(specificYearOrderedData);

        //Get String inside of an ordered finalList
        for (int i = 0; i < specificYearOrderedData.size(); i++) {
            //System.out.println(specificYearOrderedData.get(i).convertToString(specificYearOrderedData.get(i)));
            finalSpecificYearOrderedData.add(specificYearOrderedData.get(i).convertToString(specificYearOrderedData.get(i)));
        }

        return finalSpecificYearOrderedData;
    }

    /**
     * Resume : Function that Gets the Year from List Item
     * @param dataString
     * @return
     */
    public String getYear(String dataString) {
        //System.out.println("Year = " + dataString.substring(0, dataString.indexOf(',')));
        return dataString.substring(0, dataString.indexOf(','));
    }

    /**
     * Resume : Function that Gets the City from List Item
     * @param dataString
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @return
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
                //break;
            }
        }
        return specificYearList;
    }

    /**
     * Resume : Method that Sends Data to the View
     * @param specificYearData
     */
    public void getDataToDrawGhraphic(List<String> specificYearData) {
        view.drawGraphic(specificYearData);
    }

    /**
     * Resume : Method that returns a List with all the Years on a File
     * @param userChoosenFile
     * @return
     */
    public List<String> getAllYearsList(String userChoosenFile) {

        List<String> userReadedFile = readFile(userChoosenFile);
        List<String> allYearsList = new ArrayList<>();

        for (int i = 0; i < userReadedFile.size(); i++) {
            //Gets all the Years in File Choosen By The User and Puts it in all Year List
            //Checks if String Length is Greater Than 0
            if(userReadedFile.get(i).length() > 0) {
                allYearsList.add(getYear(userReadedFile.get(i)));
            }
        }
        //Calls Method to Remove Repeated Elements from List with TreeSet
        return removeRepeatedElements(allYearsList);
    }

    /**
     * Resume : Remove Repeated Elements from List with TreeSet
     * @param allYearsList
     * @return
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
     * @return
     */
    public int getQtyYearsInList(List<String> dataList) {
        List<String> yearsList = new ArrayList<>();
        dataList.removeAll(Collections.singleton(" "));
        for (int i = 0; i < dataList.size() - 1; i++) {
            //Check if String not Empty
            if(dataList.get(i).length() > 0) {
                System.out.println("String = " + dataList.get(i));
                yearsList.add(getYear(dataList.get(i)));
            }
        }
        return new TreeSet<>(yearsList).size();
    }

    /**
     * Resume : Function that gets the data to animate and draw all the graphics and uses a new Thread to JavaFx Thread be available to update View
     * @param yearDataChartRacer
     */
    public void getDataDrawAllGraphics(List<List<String>> yearDataChartRacer) {
        //Chanel Teams PO2 2019-2020 Video 4 about Threads of Professor João Paulo Barros
        new Thread( () -> {
            for (int i = 0; i < yearDataChartRacer.size(); i++) {
                view.drawAllGraphics(orderByPopulation(yearDataChartRacer.get(i), Integer.parseInt(getYear(yearDataChartRacer.get(i).get(i)))));
            }
        }).start();
    }
}
