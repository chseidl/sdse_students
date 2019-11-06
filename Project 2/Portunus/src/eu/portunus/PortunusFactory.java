package eu.portunus;

import eu.portunus.base.AbstractPortunusFactory;
import eu.portunus.core.IPasswordGroup;
import eu.portunus.core.IPasswordLibrary;
import eu.portunus.core.IPasswordRecord;
import eu.portunus.util.finder.IPasswordRecordFinder;
import eu.portunus.util.io.IPasswordLibraryLoader;
import eu.portunus.util.io.IPasswordLibrarySaver;
import eu.portunus.util.passwordgenerator.IPasswordGenerator;

public class PortunusFactory extends AbstractPortunusFactory {

	@Override
	public IPasswordLibrary createPasswordLibrary() {
		//TODO: Instantiate password library
		return null;
	}
	
	@Override
	public IPasswordGroup createPasswordGroup() {
		//TODO: Instantiate password group
		return null;
	}
	
	@Override
	public IPasswordRecord createPasswordRecord() {
		//TODO: Instantiate password record
		return null;
	}

	
	@Override
	public IPasswordLibraryLoader createPasswordLibraryLoader() {
		//TODO: Instantiate password library loader
		return null;
	}
	
	@Override
	public IPasswordLibrarySaver createPasswordLibrarySaver() {
		//TODO: Instantiate password library saver
		return null;
	}
	
	
	@Override
	public IPasswordGenerator createPasswordGenerator() {
		//TODO: Instantiate password generator
		return null;
	}

	
	@Override
	public IPasswordRecordFinder createPasswordRecordFinder() {
		//TODO: Instantiate password record finder
		return null;
	}
	
}
