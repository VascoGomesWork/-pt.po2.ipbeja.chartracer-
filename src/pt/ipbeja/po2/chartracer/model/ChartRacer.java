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
        return null;
    }
}
