package portunus.util.passwordgenerator;

import java.util.Collection;

public interface IPasswordGenerator {
	public String generatePassword(int length, Collection<CharacterSet> characterSets);
}