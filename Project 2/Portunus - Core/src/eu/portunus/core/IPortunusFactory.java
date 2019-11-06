package eu.portunus.core;

import eu.portunus.util.finder.IPasswordRecordFinder;
import eu.portunus.util.io.IPasswordLibraryLoader;
import eu.portunus.util.io.IPasswordLibrarySaver;
import eu.portunus.util.passwordgenerator.IPasswordGenerator;

public interface IPortunusFactory {
	public IPasswordLibrary createPasswordLibrary();

	public IPasswordGroup createPasswordGroup();
	public IPasswordGroup createPasswordGroup(String title);

	public IPasswordRecord createPasswordRecord();
	public IPasswordRecord createPasswordRecord(String title);
	
	
	public IPasswordLibraryLoader createPasswordLibraryLoader();
	public IPasswordLibrarySaver createPasswordLibrarySaver();
	
	
	public IPasswordGenerator createPasswordGenerator();
	
	public IPasswordRecordFinder createPasswordRecordFinder();
}