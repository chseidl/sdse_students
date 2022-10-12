package edu.sdse.csvprocessor;

public class CityRecord { //extends Object is implied
	private int id; // why set this private, then make a get method?
	private int year;
	private String city;
	private int population;
	
	public CityRecord(int id, int year, String city, int population) {
		this.id = id; // id alone refers to the id in the input, which is why we need this.id
		this.year = year;
		this.city = city;
		this.population = population;
	}
	
	// the toString method is inherited when creating an object class, because object class is an abstract class with this method
	@Override
	public String toString() {
		String s;
		s = "id: "+ id + ", year: " + year + ", city: " + city +", population: " + population;
		return s;
	}
	
	public int getID() {
		return id;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getPopulation() {
		return population;
	}

}

