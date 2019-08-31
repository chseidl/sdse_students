package edu.sdse.javafx.chart;

public class CityRecord {

	private int id;
	private int year;
	private String name;

	public CityRecord(int id, int year, String name) {
		this.id = id;
		this.year = year;
		this.name = name;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return id;
	}

	public int getYear() {
		return year;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		//TODO: Override this to properly print city records.
		return super.toString();
	}
}
