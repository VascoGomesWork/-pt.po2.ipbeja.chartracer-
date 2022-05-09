package pt.ipbeja.po2.chartracer.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> readFile2(String fileName){

        List<String> stringData = new ArrayList<>();
        //TODO - Get the Length of the file
        char[] fileData = new char[999999999];
        try {
            FileReader fileReader = new FileReader(fileName);
            try {

                //reads all data to a char array
                fileReader.read(fileData);

                for (char dataFile : fileData) {
                    stringData.add(makeString(dataFile));
                    //System.out.println(dataFile);
                }
                //System.out.println(stringData);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String makeString(char dataFile) {
        this.dataString += dataFile;
        return this.dataString;
    }
}
