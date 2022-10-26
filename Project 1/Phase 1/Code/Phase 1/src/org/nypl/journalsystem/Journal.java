package org.nypl.journalsystem;
import java.util.ArrayList;

public class Journal {
	public String name;
	public String publisher;
	public String ISSN;
	public ArrayList<Article> articles;
	
	public Journal (String name, String publisher, String ISSN)
	{
		// constructor
		this.name = name;
		this.publisher = publisher;
		this.ISSN = ISSN;
	}
	
	public void is_full() {
		// check if journal has at least three articles
	}
	
	public void is_not_empty() {
		// check if journal is empty
		
	}
	
	public void add_article() {
		// add articles to journal
	}
	
	public String toString() {
		// override the toString method for printing
		String s;
		s = "name: "+name+ ", publisher: " + publisher + ", ISSN: " + ISSN +";
		return s;
	}
	
}
