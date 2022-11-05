package org.nypl.journalsystem;

public class Author {
	private int ID;
	private String last_name;
	private String first_name;

	public Author(int ID, String last_name, String first_name) {
		// constructor
		this.ID = ID;
		this.last_name = last_name;
		this.first_name = first_name;		
	}
	
	public String toString() {
		// override the toString method for printing
		String s;
		s = ID+"";
		return s;
	}
	
	public int get_ID() {
		return this.ID;
	}
	
}
