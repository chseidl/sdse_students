package eu.portunus.util.finder;

import java.util.ArrayList;
import java.util.List;

import eu.portunus.core.IPasswordLibrary;
import eu.portunus.core.IPasswordRecord;

public class PasswordRecordFinder implements IPasswordRecordFinder {
	@Override
	public List<IPasswordRecord> findMatchingPasswordRecords(String partialString, IPasswordLibrary passwordLibrary) {
		List<IPasswordRecord> matchingPasswordRecords = new ArrayList<IPasswordRecord>();
		
		//TODO: Search matching password records
		
		return matchingPasswordRecords;
	}
}
