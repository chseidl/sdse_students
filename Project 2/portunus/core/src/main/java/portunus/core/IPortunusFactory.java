package portunus.core;

import portunus.util.finder.IPasswordRecordFinder;
import portunus.util.io.IPasswordLibraryLoader;
import portunus.util.io.IPasswordLibrarySaver;
import portunus.util.passwordgenerator.IPasswordGenerator;

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