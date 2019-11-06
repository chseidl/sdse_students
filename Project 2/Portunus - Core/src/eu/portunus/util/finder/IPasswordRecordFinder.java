package eu.portunus.util.finder;

import java.util.List;

import eu.portunus.core.IPasswordLibrary;
import eu.portunus.core.IPasswordRecord;

public interface IPasswordRecordFinder {
	public List<IPasswordRecord> findMatchingPasswordRecords(String partialString, IPasswordLibrary passwordLibrary);
}