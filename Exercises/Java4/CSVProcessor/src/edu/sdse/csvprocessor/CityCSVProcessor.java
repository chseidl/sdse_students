package edu.sdse.csvprocessor;
//import edu.sdse.CityRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CityCSVProcessor {
	private ArrayList<CityRecord> allRecords;
	private Map<String, List<CityRecord>> cityRecordsMap;
	
	// Constructor
	public CityCSVProcessor() {
		allRecords = new ArrayList<>();
		cityRecordsMap = new HashMap<>();
	}
	
	public void readAndProcess(File file) {
		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			//Discard header row
			br.readLine();
		
			String line;

			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
								
				CityRecord cityRecord = new CityRecord(id, year, city, population);
				allRecords.add(cityRecord); // add to list of all records
				//System.out.println(cityRecord); // print city record
				
				// check if city key in map
				if (cityRecordsMap.get(city) == null) {
					// if empty, add data to map
					cityRecordsMap.put(city, new ArrayList<CityRecord>()); // put key and empty list
				}
				cityRecordsMap.get(city).add(cityRecord);
				System.out.println(cityRecordsMap.get(city));
			}
			
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
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
		
		return rawValue;
	}
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);
	}
	
	
	
	/*private void processRecords {
		// this will be the processor.... blah blah...
		//
		
	}*/
}


