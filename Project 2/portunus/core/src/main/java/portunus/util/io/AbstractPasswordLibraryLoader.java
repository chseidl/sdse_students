package portunus.util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import portunus.core.IPasswordLibrary;
import portunus.util.crypter.DecryptionFailedException;

public abstract class AbstractPasswordLibraryLoader implements IPasswordLibraryLoader {

	@Override
	public IPasswordLibrary load(File file, String masterPassword, IPasswordLibrary passwordLibrary) throws FileNotFoundException, DecryptionFailedException {
		String encryptedXMLContent = loadFromFile(file);
		String decryptedXMLContent = decryptXMLContent(encryptedXMLContent, masterPassword);
		
		decodeFromXML(decryptedXMLContent, passwordLibrary);
		
		return passwordLibrary;
	}

	protected String loadFromFile(File file) throws FileNotFoundException {
		if (file == null) {
			throw new FileNotFoundException();
		}
		
		if (file == null || !file.exists()) {
			throw new FileNotFoundException("File " + file.getPath() + " could not be found.");
		}
		
		try {
			byte[] fileContent = Files.readAllBytes(file.toPath());
			return new String(fileContent, StandardCharsets.UTF_8);
		} catch(IOException e) {
			e.printStackTrace();
			throw new FileNotFoundException("File " + file.getPath() + " could not be read.");
		}
	}
	
	protected abstract String decryptXMLContent(String encryptedXMLContent, String masterPassword) throws DecryptionFailedException;
	protected abstract void decodeFromXML(String xmlContent, IPasswordLibrary passwordLibrary);
}
