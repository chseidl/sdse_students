package org.nypl.journalsystem;
import java.util.ArrayList;

public class Article {
	private int ID;
	private String ISSN;
	private String title;
	private ArrayList<Author> authors;

	public Article(int ID, String title, ArrayList<Author> authors,String ISSN) {
		// constructor
		this.ID = ID;
		this.ISSN = ISSN;
		this.title = title;
		this.authors = authors;
	}
	
	public String toString() {
		// override the toString method for printing
		String s;
		s = "\nArticle title: "+title+ ", Authors: " + authors;
		return s;
	}
	
}
