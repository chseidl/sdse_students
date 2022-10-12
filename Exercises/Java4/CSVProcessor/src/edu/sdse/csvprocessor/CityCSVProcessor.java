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
				//Parse each line
				String[] rawValues = line.split(",");
				
				//Get individual values from each line
				//Convert into appropriate data type
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
								
				//initialize new record
				CityRecord cityRecord = new CityRecord(id, year, city, population);
				
				//Put data into a record
				allRecords.add(cityRecord); // add to list of all records
				//System.out.println(cityRecord); // print city record
				
				// check if city key in map
				if (cityRecordsMap.get(city) == null) {
					// if empty, add an empty list to the map map
					cityRecordsMap.put(city, new ArrayList<CityRecord>()); // put key and empty list
				}
				
				// add the record to the map
				cityRecordsMap.get(city).add(cityRecord);
				//System.out.println(cityRecordsMap.get(city));
			}
			computeStats(cityRecordsMap);
			
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
	
	public void computeStats(Map<String, List<CityRecord>> cityMap) {
		
		for (Map.Entry<String, List<CityRecord>> entry : cityRecordsMap.entrySet()) {
			//System.out.println(entry.getKey()+entry.getValue());
			List<CityRecord> recordList = entry.getValue();
			String city = entry.getKey();
			int entries = recordList.size();
			
			int min = 2050;
			int max = 0;
			int popSum = 0;
			
			for (CityRecord record : recordList) {
				System.out.println(record);
				
				int year = record.getYear();
				int population = record.getPopulation();
				
				if (year > max) {
					max = year;
				}
				if (year < min) {
					min = year;
				}
				popSum += population;
			}
			int avePop = popSum/entries;
			System.out.println(String.format("Average population of %s (%d-%d; %d Entries): %d ",city,min,max,entries,avePop));
		}
	}
	
}


