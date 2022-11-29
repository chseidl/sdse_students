package eu.portunus.util.passwordgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class PasswordGenerator implements IPasswordGenerator {
	private String password;
	
	@Override
	public String generatePassword(int length, Collection<CharacterSet> characterSets) {
		Random randomChoice = new Random();
		password = new String(); //initialize new empty string for password
		//CharacterSet.DIGITS.getSymbols();
		
		//if characterSets is null or empty, or length is negative: return empty string
		if (characterSets == null || characterSets.isEmpty() || length <= 0) {
			return "";
		}
	
		//combine character sets to one string
		String combinedCharSet = "";
		for (CharacterSet cset : characterSets) {
			combinedCharSet = combinedCharSet.concat(cset.getSymbols());
		}
		
		//randomly select chars for password
		for (int i=0; i<length; i++){
		    int index = randomChoice.nextInt(combinedCharSet.length());
		    password += combinedCharSet.charAt(index);
		}	
		
		return password;
	}
	
	/* main method for testing functionality */
	public static void main(String[] args) { 
		PasswordGenerator passGenerator = new PasswordGenerator(); 
	
		//password with full character set
		Collection<CharacterSet> fullCharset = Arrays.asList(CharacterSet.values()); 
		String passwordl = passGenerator.generatePassword(15, fullCharset); 
		System.out.println("First password: \n"+passwordl+"\n"); 
		
		//password without digits
		Collection<CharacterSet> noDigits = new ArrayList<>(Arrays.asList(CharacterSet.values())); 
		noDigits.remove(CharacterSet.DIGITS); 
		String password2 = passGenerator.generatePassword(50, noDigits); 
		System.out.println("Second password: \n"+password2+"\n"); 
		
		//password with only lower-case and digits
		Collection<CharacterSet> lowerAndDigit = new ArrayList<>(Arrays.asList(CharacterSet.LOWER_CASE, CharacterSet.DIGITS)); 
		String password3 = passGenerator.generatePassword(15, lowerAndDigit); 
		System.out.println("Password 3:\n"+password3); 
	} 
	
}
