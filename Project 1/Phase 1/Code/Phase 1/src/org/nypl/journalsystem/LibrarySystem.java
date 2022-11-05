package org.nypl.journalsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class LibrarySystem {
	// make hash map of journal, author,
	private Map<String, Journal> journals;
	private Map<Integer, Author> authors;
	public Map<Integer, Article> articles;
	
	public LibrarySystem() {
		//Initialize system with default journals.
		//Hash Map of journals
		journals = new HashMap<>();
		
		//make new publishers
		Publisher publisher1 = new Publisher("Springer", "Germany");
		Publisher publisher2 = new Publisher("Elsevier","Netherlands");
		Publisher publisher3 = new Publisher("Nature Research","Great Britain");
		
		//make new journals
		Journal journal1 = new Journal("Higher Education", publisher1, "0018-1560"); //ISSN is like ID
		Journal journal2 = new Journal("System", publisher2, "0346-2511");
		Journal journal3 = new Journal("Chem", publisher2, "2451-9294");
		Journal journal4 = new Journal("Nature", publisher3, "1476-4687");
		Journal journal5 = new Journal("Society", publisher1, "0147-2011");

		//add journals to HashMap
		journals.put(journal1.get_ISSN(), journal1);
		journals.put(journal2.get_ISSN(), journal2);
		journals.put(journal3.get_ISSN(), journal3);
		journals.put(journal4.get_ISSN(), journal4);
		journals.put(journal5.get_ISSN(), journal5);
		//System.out.println(journals.size());
		//System.out.println(journals);		
	}
	
	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		loadArticles();
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		//Load authors from file 
		authors = new HashMap<>();
		
		Reader file = new FileReader("data/Authors.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(file);
		for (CSVRecord record : records) {
		    int id = convertToInt(record.get(0)); 
		    String last_name = convertToString(record.get(1));
		    String first_name = convertToString(record.get(2));
		    
		    Author author = new Author(id, last_name, first_name);
		    //System.out.println(author.toString());
		    //1,"Erickson, Esmae"
		    authors.put(id, author);   
		}
	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		//Load articles from file and assign them to appropriate journal
		articles = new HashMap<>();
		
		Reader file = new FileReader("data/Articles.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(file);
		for (CSVRecord record : records) {
		    int id = convertToInt(record.get(0));
		    String title = record.get(1);

		    String[] authorIDs = record.get(2).replace("[", "").replace("]", "").replace(" ","").split(";");
		    
		    //convert authorIDs to an ArrayList
		    ArrayList<String> authorID_list = new ArrayList<String>(Arrays.asList(authorIDs));		    
		    
		    // get ArrayList of Author objects from authors HashMap
		    ArrayList<Author> author_objects = new ArrayList<Author>();
		    for (String auth : authorID_list) {
		    	author_objects.add(authors.get(Integer.parseInt(auth)));
		    }
		    String ISSN = record.get(3).trim();
		    
		    //create new article and add to articles HashMap
		    Article article = new Article(id, title, author_objects, ISSN);
		    articles.put(id, article); 
		    
		    //assign article to appropriate journal
		    Journal journal = journals.get(ISSN);//.add_article(article);
		    journal.add_article(article);
		}
	}
	
	public void listContents() {
		//Print all journals with their respective articles and authors to the console. journal-->articles-->author
		for (String key: journals.keySet()) {
			System.out.println(journals.get(key));
		}
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue.replaceAll("\"", "").replaceAll(";", "");
	}
	
	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		librarySystem.listContents();
	}
}
