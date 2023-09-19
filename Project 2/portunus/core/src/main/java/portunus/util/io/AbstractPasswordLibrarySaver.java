package portunus.util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import portunus.core.IPasswordLibrary;
import portunus.util.crypter.EncryptionFailedException;

public abstract class AbstractPasswordLibrarySaver implements IPasswordLibrarySaver {
	@Override
	public void save(IPasswordLibrary passwordLibrary, File file, String masterPassword) throws EncryptionFailedException {
		String xmlContent = encodeAsXML(passwordLibrary);
		String encryptedXMLContent = encryptXMLContent(xmlContent, masterPassword);
		saveToFile(encryptedXMLContent, file);
	}
	
	protected abstract String encodeAsXML(IPasswordLibrary passwordLibrary);
	protected abstract String encryptXMLContent(String xmlContent, String masterPassword) throws EncryptionFailedException;
	
	protected void saveToFile(String content, File file) {
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
			writer.write(content);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			//TODO: Better error handling.
		} catch(IOException e) {
			e.printStackTrace();
			//TODO: Better error handling.
		}
	}
}
