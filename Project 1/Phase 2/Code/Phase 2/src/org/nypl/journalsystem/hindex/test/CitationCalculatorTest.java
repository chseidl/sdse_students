package org.nypl.journalsystem.hindex.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.nypl.journalsystem.hindex.CitationCalculator;
import org.nypl.journalsystem.hindex.ICitationCalculator;

public class CitationCalculatorTest {
	private ICitationCalculator calculator;
	
	@BeforeEach
	public void setup() {
		calculator = new CitationCalculator();
	}
	
	@AfterEach
	public void tearDown() {
		calculator = null;
	}

	//TODO: Implement test cases for the citation calculator
}
