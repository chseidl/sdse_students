package portunus.util;

import portunus.core.IPasswordGroup;
import portunus.core.IPasswordLibrary;
import portunus.core.IPasswordRecord;
import portunus.core.IPortunusFactory;

public class ExamplePasswordLibraryCreator {
	public IPasswordLibrary createExampleLibrary(IPortunusFactory factory) {
		IPasswordLibrary passwordLibrary = factory.createPasswordLibrary();
		
		
		IPasswordGroup personalGroup = factory.createPasswordGroup("Personal");
		passwordLibrary.addEntry(personalGroup);
		
		IPasswordRecord bankRecord = createPasswordRecord(factory, "Bank", "MrMoneyMaker", "MoneyMakesTheWorldGoRound!", "http://www.bank.com");
		personalGroup.addEntry(bankRecord);
		
		IPasswordRecord amazonRecord = createPasswordRecord(factory, "Amazon", "ShoeShopper0815", "ShoppingIsMyLife", "http://www.amazon.com");
//		IPasswordRecord amazonRecord = createPasswordRecord(factory, "Amazon", "ShoeShopper0815", "§%!db=%GHFU", "http://www.amazon.com");
		personalGroup.addEntry(amazonRecord);
		
		
		IPasswordGroup professionalGroup = factory.createPasswordGroup("Professional");
		passwordLibrary.addEntry(professionalGroup);
		
		IPasswordRecord googleRecord = createPasswordRecord(factory, "Google", "peter@gmail.com", "SuperSecurePassword", "http://www.google.com", "For Docs, Calc etc.");
		professionalGroup.addEntry(googleRecord);
		
		IPasswordGroup workGroup = factory.createPasswordGroup("Work");
		professionalGroup.addEntry(workGroup);
		
		IPasswordGroup collaborationsGroup = factory.createPasswordGroup("Collaborations");
		professionalGroup.addEntry(collaborationsGroup);
		
		
		IPasswordRecord workServerRecord = createPasswordRecord(factory, "Work Server", "Admin", "admin123");
		workGroup.addEntry(workServerRecord);
		
		
		return passwordLibrary;
	}
	
	private static IPasswordRecord createPasswordRecord(IPortunusFactory factory, String title, String user, String password) {
		IPasswordRecord passwordRecord = factory.createPasswordRecord(title);
		
		passwordRecord.setUser(user);
		passwordRecord.setPassword(password);
		
		return passwordRecord;
	}
	
	private static IPasswordRecord createPasswordRecord(IPortunusFactory factory, String title, String user, String password, String url) {
		IPasswordRecord passwordRecord = createPasswordRecord(factory, title, user, password);
		
		passwordRecord.setUrl(url);
		
		return passwordRecord;
	}
	
	private static IPasswordRecord createPasswordRecord(IPortunusFactory factory, String title, String user, String password, String url, String notes) {
		IPasswordRecord passwordRecord = createPasswordRecord(factory, title, user, password, url);
		
		passwordRecord.setNotes(notes);
		
		return passwordRecord;
	}
}
