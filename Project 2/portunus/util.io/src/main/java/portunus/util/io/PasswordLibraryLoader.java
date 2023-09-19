package portunus.util.io;

import portunus.core.IPasswordLibrary;
import portunus.util.crypter.DecryptionFailedException;

public class PasswordLibraryLoader extends AbstractPasswordLibraryLoader {
	
	@Override
	protected String decryptXMLContent(String encryptedXMLContent, String masterPassword) throws DecryptionFailedException {
		// TODO: Decrypt XML content.
		return encryptedXMLContent;
	}
	
	@Override
	protected void decodeFromXML(String xmlContent, IPasswordLibrary passwordLibrary) {
		//TODO: Read (plain text) XML content and fill the password library with the respective records and groups.
		//You can use the (plain-XML) example data in the root of the project to test the loader. 
	}
}
