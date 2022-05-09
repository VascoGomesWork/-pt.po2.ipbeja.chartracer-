package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
        List<String> fileData = null;
        try {

            fileData = Files.readAllLines(Paths.get(fileName));
            assertEquals(fileData, chartRacer.readFile(fileName));
            //chartRacer.readFile2(fileName);
            //assertEquals(fileData, chartRacer.readFile2(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void teste2() {

    }
}