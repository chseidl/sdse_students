package portunus.util.io;

import java.io.File;
import java.io.FileNotFoundException;

import portunus.core.IPasswordLibrary;
import portunus.util.crypter.DecryptionFailedException;

public interface IPasswordLibraryLoader {
	public IPasswordLibrary load(File file, String masterPassword, IPasswordLibrary passwordLibrary) throws FileNotFoundException, DecryptionFailedException;
}
