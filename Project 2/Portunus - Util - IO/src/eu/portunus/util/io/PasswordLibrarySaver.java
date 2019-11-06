package eu.portunus.util.io;

import eu.portunus.core.IPasswordLibrary;
import eu.portunus.util.crypter.EncryptionFailedException;

public class PasswordLibrarySaver extends AbstractPasswordLibrarySaver {
	@Override
	protected String encodeAsXML(IPasswordLibrary passwordLibrary) {
		//TODO: Write the password library to (plain text) XML by encoding the respective records and groups.
		return "";
	}
	
	@Override
	protected String encryptXMLContent(String xmlContent, String masterPassword) throws EncryptionFailedException {
		//TODO: Encrypt XML content.
		return xmlContent;
	}
}
