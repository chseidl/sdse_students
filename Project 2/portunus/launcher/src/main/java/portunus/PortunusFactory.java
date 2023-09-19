package portunus;

import portunus.base.AbstractPortunusFactory;
import portunus.core.IPasswordGroup;
import portunus.core.IPasswordLibrary;
import portunus.core.IPasswordRecord;
import portunus.util.finder.IPasswordRecordFinder;
import portunus.util.io.IPasswordLibraryLoader;
import portunus.util.io.IPasswordLibrarySaver;
import portunus.util.passwordgenerator.IPasswordGenerator;

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
