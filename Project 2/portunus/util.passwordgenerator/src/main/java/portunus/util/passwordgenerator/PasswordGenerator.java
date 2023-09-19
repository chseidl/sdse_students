package portunus.util.passwordgenerator;

import java.util.Collection;

public class PasswordGenerator implements IPasswordGenerator {
	@Override
	public String generatePassword(int length, Collection<CharacterSet> characterSets) {
		if (characterSets == null || characterSets.isEmpty()) {
			return "";
		}
		
		//TODO: Implement the password generator.
		
		return "password";
	}
}
