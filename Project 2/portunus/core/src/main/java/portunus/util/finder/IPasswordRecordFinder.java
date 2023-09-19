package portunus.util.finder;

import java.util.List;

import portunus.core.IPasswordLibrary;
import portunus.core.IPasswordRecord;

public interface IPasswordRecordFinder {
	public List<IPasswordRecord> findMatchingPasswordRecords(String partialString, IPasswordLibrary passwordLibrary);
}