package portunus.util.io;

import java.io.File;

import portunus.core.IPasswordLibrary;
import portunus.util.crypter.EncryptionFailedException;

public interface IPasswordLibrarySaver {
	public void save(IPasswordLibrary passwordLibrary, File file, String masterPassword) throws EncryptionFailedException;
}
