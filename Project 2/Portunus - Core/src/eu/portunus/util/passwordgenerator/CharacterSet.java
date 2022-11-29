package eu.portunus.util.passwordgenerator;

public enum CharacterSet {
	UPPER_CASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
	LOWER_CASE("abcdefghijklmnopqrstuvwxyz"),
	DIGITS("0123456789"),
	MINUS("-"),
	UNDERSCORE("_"),
	SPACE(" ");
	
	private String symbols; 

    private CharacterSet(String symbols) {
        this.symbols = symbols;
    }

    public String getSymbols() {
        return symbols;
    }
}
