package org.nypl.journalsystem;
import java.util.ArrayList;

public class Journal {
	public String name;
	public Publisher publisher;
	public String ISSN;
	public ArrayList<Article> articles;
	public Boolean is_empty;
	private int article_count;
	
	public Journal (String name, Publisher publisher, String ISSN)
	{
		// constructor
		this.name = name;
		this.publisher = publisher;
		this.ISSN = ISSN;
		this.is_empty = true;
		this.article_count = 0;
		this.articles = new ArrayList<>();
	}
	
	public boolean is_full() {
		// check if journal has at least three articles
		if (this.article_count < 3) return false;
		else return true;
	}
	
	public boolean is_not_empty() {
		// check if journal is empty
		return this.is_empty;
	}
	
	public void add_article(Article article) {
		// add articles to journal
		this.articles.add(article);
		this.is_empty = false;
		this.article_count += 1;
	}
	
	public String get_ISSN() {
		return this.ISSN;
		
	}
	
	public String toString() {
		// override the toString method for printing
		String s;
		s = "name: "+name+ ", publisher: " + publisher + ", ISSN: " + ISSN + "articles: "+ articles;
		return s;
	}
	
}
