package org.nypl.journalsystem.ui;

import java.util.List;

import org.nypl.journalsystem.core.IAuthor;
import org.nypl.journalsystem.core.ILibrarySystem;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

public class LibrarySystemApplication extends LibrarySystemApplicationBase {
	
	/**
	 * Creates the library system and initializes it with the stored data.
	 * 
	 *  @return The library system.
	 */
	@Override
	protected ILibrarySystem loadLibrarySystem() throws Exception {
		//TODO: Implement
		return null;
	}
	
	/**
	 * Creates a pie chart and initializes it with the co-author data of the provided author so 
	 * that the pie chart contains all known co-authors and the slices have an appropriate size
	 * for the number of articles the authors had together.
	 * 
	 * For example, if the original author had two co-authors A and B with three articles together
	 * with A and only one article together with B, the slice for A should take up 3/4 of the pie
	 * chart.
	 * 
	 * @param originalAuthor The author for whom to determine the co-authors.
	 * @return The pie chart of co-authors.
	 */
	@Override
	protected PieChart createCoAuthorsChart(IAuthor originalAuthor) {
		//TODO: Implement
		return null;
	}
	

	/**
	 * Creates a bar chart and initializes it with the h-index values of all provided authors in 
	 * comparison with an individual bar for each author.
	 * 
	 * @param authors The authors for whom to calculate (and visually compare) the h-index values.
	 * 
	 * @return The bar chart of h-index values.
	 */
	@Override
	protected BarChart<String, Number> createHIndexChart(List<IAuthor> authors) {
		//TODO: Implement
		return null;
	}
	
	
    public static void main(String[] args) {
        launch(args);
    }
}
