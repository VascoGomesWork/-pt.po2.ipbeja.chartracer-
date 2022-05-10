package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 09/05/2022
 */
public class ChartRacer {

    String dataString = "";



    /**
     * Resume : Method to Read Cities Data from the File
     * @param fileName
     * @return
     */
    public List<String> readFile(String fileName) {
        try {
            return makeNewList(Files.readAllLines(Paths.get(fileName)));
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
    public List<String> makeNewList(List<String> citiesList) {

        //Removes the first lines until the first number 12 appears in the file
        citiesList.subList(0, citiesList.indexOf("12")).clear();
        //Removes all numbers 12 and spaces
        citiesList.removeAll(Collections.singleton("12"));
        citiesList.removeAll(Collections.singleton(" "));

        /*String newCitiesDataString = "";
        List<String> newCitiesList = new ArrayList<>();

        //makes new string to join every line of the file
        /*for (String string : citiesList) {
            newCitiesDataString += string;
            if(string.equals("\n")){
                newCitiesList.add(newCitiesDataString);
            }
        }*/
        return citiesList;
    }

    public List<String> orderData(List<String> fileData) {
        OrderCitiesData orderCitiesData = new OrderCitiesData();
        orderCitiesData.compareTo(fileData);
        return new ArrayList<>();
    }

    public List<String> getDataYear(List<String> fileData, String year) {

        List<String> newYearList = new ArrayList<>();

        for (int i = 0; i < fileData.size(); i++) {
            //Gets the Year of every list entrance from the beginning to the first ',' and checks if it's equal to the year inserted
            try{
                if(fileData.get(i).substring(0, fileData.get(i).indexOf(',')).equals(year)){
                    newYearList.add(fileData.get(i));
                }
            }
            catch (Exception e){
                break;
            }
        }
        return newYearList;
    }
}
