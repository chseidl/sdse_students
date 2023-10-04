package edu.sdse.csvprocessor;

public class CityRecord {
    private int id;
    private int year;
    private String city;
    private int population;

    public CityRecord(int id, int year, String city, int population) {
        this.id = id;
        this.year = year;
        this.city = city;
        this.population = population;
    }
    
    @Override
    public String toString() {
        return String.format("id: %d, year: %d, city: %s, population: %d", id, year, city, population);
    }
}



