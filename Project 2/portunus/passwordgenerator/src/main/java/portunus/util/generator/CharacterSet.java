package portunus.util.generator;

public enum CharacterSet {
	UPPER_CASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
	LOWER_CASE("abcdefghijklmnopqrstuvwxyz"),
	DIGITS("0123456789"),
	MINUS("-"),
	UNDERSCORE("_"),
	SPACE(" "),
	SPECIAL("!$%&"); //TODO: Complete
	
	private String characters;
	
	private CharacterSet(String characters) {
		this.characters = characters;
	}
	
	public String getCharacters() {
		return characters;
	}
	
	public boolean contains(char character) {
		return characters.indexOf(character) >= 0; 
	}
}
