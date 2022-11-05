package org.nypl.journalsystem;

public class Publisher {
	private String name;
	private String location;
	
	public Publisher(String name, String location) {
		// constructor
		this.name = name;
		this.location = location;
	}
	
	public String toString() {
		// override the toString method for printing
		String s;
		s = "publisher: "+name+", location: " + location;
		return s;
	}
}
