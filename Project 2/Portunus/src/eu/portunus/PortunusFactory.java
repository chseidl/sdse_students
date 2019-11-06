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
		return null;
	}
	
	@Override
	public IPasswordGroup createPasswordGroup() {
		return null;
	}
	
	@Override
	public IPasswordRecord createPasswordRecord() {
		return null;
	}

	
	@Override
	public IPasswordLibraryLoader createPasswordLibraryLoader() {
		return null;
	}
	
	@Override
	public IPasswordLibrarySaver createPasswordLibrarySaver() {
		return null;
	}
	
	
	@Override
	public IPasswordGenerator createPasswordGenerator() {
		return null;
	}

	
	@Override
	public IPasswordRecordFinder createPasswordRecordFinder() {
		return null;
	}
	
}
