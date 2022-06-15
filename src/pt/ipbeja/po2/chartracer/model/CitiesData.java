package pt.ipbeja.po2.chartracer.model;

/**
 * @author Vasco Gomes 19921
 * @date 10/05/2022
 */
public class CitiesData implements Comparable<CitiesData>{

    private final int populationByCity;
    private final int year;
    private final String city;
    private final String country;
    private final String region;

    /**
     * Resume: Constructor
     * @param year
     * @param city
     * @param country
     * @param populationByCity
     * @param region
     */
    public CitiesData(String year, String city, String country, String populationByCity, String region) {

        this.year = Integer.parseInt(year);
        this.city = city;
        this.country = country;
        this.populationByCity = Integer.parseInt(populationByCity);
        this.region = region;
    }

    /**
     * Resume: Function that Gets the City Population
     * @return: City Population
     */
    public int getCityPopulation() {
        return this.populationByCity;
    }

    /**
     * Resume: Function that compares Objects
     * @param o
     * @return: Number Accordingly to the Object Comparation
     */
    @Override
    public int compareTo(CitiesData o) {
        if(this.getCityPopulation() == o.getCityPopulation()) return 0;
        else if(this.getCityPopulation() > o.getCityPopulation()) return -1;
        else return 1;
    }

    /**
     * Resume: Function that Converts Object to String
     * @param citiesData
     * @return: Object Converted into String
     */
    public String convertToString(CitiesData citiesData) {
        return citiesData.year + "," + citiesData.city + "," + citiesData.country + "," + citiesData.populationByCity + "," + citiesData.region;
    }
}
