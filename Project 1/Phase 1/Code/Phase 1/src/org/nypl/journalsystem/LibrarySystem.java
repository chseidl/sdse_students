package org.nypl.journalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LibrarySystem {
	
	public LibrarySystem() {
		//TODO: Initialize system with default journals.
	}
	
	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		loadArticles();
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		File file = new File("data/Authors.csv");

		//TODO: Load authors from file 
	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		File file = new File("data/Articles.csv");

		//TODO: Load articles from file and assign them to appropriate journal
	}
	
	
	public void listContents() {
		//TODO: Print all journals with their respective articles and authors to the console.
	}
	
	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		librarySystem.listContents();
	}
}
