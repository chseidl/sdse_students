package edu.sdse.csvprocessor;

public class CityRecord {
	public int id;
	public int year;
	public String city;
	public int population;
	
	public CityRecord(int id, int year, String city, int population) {
		this.id = id;
		this.year = year;
		this.city = city;
		this.population = population;
	}
	
	public String toString() {
		String s;
		s = "id: "+this.id +", year: "+this.year+", city: "+this.city+", population: "+ this.population;
		return s;
	}
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	//}
	
}

