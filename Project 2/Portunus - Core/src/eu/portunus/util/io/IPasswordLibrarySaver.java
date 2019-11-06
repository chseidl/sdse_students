package eu.portunus.util.io;

import java.io.File;

import eu.portunus.core.IPasswordLibrary;
import eu.portunus.util.crypter.EncryptionFailedException;

public interface IPasswordLibrarySaver {
	public void save(IPasswordLibrary passwordLibrary, File file, String masterPassword) throws EncryptionFailedException;
}
