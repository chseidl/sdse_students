package edu.sdse.csvparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CityCSVParser {
	public void readAndProcess(File file) {
		List<CityRecord> allRecords = readRecordsFromFile(file);
		
		//TODO: Clean records
		
		//TODO: Collect records by city
		
		//TODO: Process records
		
		//TODO: Output results
		System.out.println("TODO: Implement program logic");
	}
	
	private List<CityRecord> readRecordsFromFile(File file) {
		List<CityRecord> allRecords = new ArrayList<>();
		
		//TODO: Read records from file
		
		return allRecords;
	}
	
	public static final void main(String[] args) {
		CityCSVParser reader = new CityCSVParser();
		
		File dataDirectory = new File("data/");
		
		//TODO: Be sure to parse all of these data sets successfully
		File fileCleanShort = new File(dataDirectory, "CitiesCleanShort.csv");
		File fileCleanLong = new File(dataDirectory, "CitiesCleanLong.csv");
		File fileMessyShort = new File(dataDirectory, "CitiesMessyShort.csv");
		File fileMessyLong = new File(dataDirectory, "CitiesMessyLong.csv");
		
		reader.readAndProcess(fileCleanShort);
	}
}
