package eu.portunus.util.passwordgenerator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import eu.portunus.util.passwordgenerator.CharacterSet;
import eu.portunus.util.passwordgenerator.PasswordGenerator;

public class PasswordGeneratorTest {
	//Tests for the password generator.
	
	@Test
	public void testNlong() {
		//Test if password is N char long when given N
		PasswordGenerator p = new PasswordGenerator();
		Collection<CharacterSet> fullCharset = Arrays.asList(CharacterSet.values());
		String s = p.generatePassword(4,fullCharset);
		int len = s.length();
		assertEquals(4, len);
	}
	
	@Test
	public void testNegative() {
		//Test if empty string is generated when N is negative
		PasswordGenerator p = new PasswordGenerator();
		String s = p.generatePassword(-1, null); // must add char set
		assertEquals("", s);
	}
	
	@Test
	public void testNoCharSet() {
		//Test if empty string is generated when no char set provided
		PasswordGenerator p = new PasswordGenerator();
		String s = new String();
		s = p.generatePassword(10, null);
		assertEquals("", s);
	}
	
	@Test
	public void testCharSet() {
		//Test if password contains only chars from char set
		PasswordGenerator p = new PasswordGenerator();
		Collection<CharacterSet> noDigits = new ArrayList<>(Arrays.asList(CharacterSet.values())); 
		noDigits.remove(CharacterSet.DIGITS);
		String s = p.generatePassword(10, noDigits); 
		
		String combinedCharSet = "";
		for (CharacterSet cset : noDigits) {
			combinedCharSet = combinedCharSet.concat(cset.getSymbols());
		}
				
		boolean check = true;
		for (int i = 0; i < s.length(); i++) {
			String chr = String.valueOf(s.charAt(i));
			//if not in combinedCharSet
			if (!(combinedCharSet.contains(chr))){
				check = false;
			}
		}
		assertEquals(check, true);
	}
}
