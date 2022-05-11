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

    public CitiesData(String year, String city, String country, String populationByCity, String region) {

        this.year = Integer.parseInt(year);
        this.city = city;
        this.country = country;
        this.populationByCity = Integer.parseInt(populationByCity);
        this.region = region;
    }

    public int getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public int getCityPopulation() {
        return this.populationByCity;
    }

    @Override
    public int compareTo(CitiesData o) {
        if(this.getCityPopulation() == o.getCityPopulation()) return 0;
        else if(this.getCityPopulation() > o.getCityPopulation()) return 1;
        else return -1;
    }

    public String convertToString(CitiesData citiesData) {
        return citiesData.year + "," + citiesData.city + "," + citiesData.country + "," + citiesData.populationByCity + "," + citiesData.region;
    }
}
