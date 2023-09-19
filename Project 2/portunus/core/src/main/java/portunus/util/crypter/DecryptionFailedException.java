package portunus.util.crypter;

public class DecryptionFailedException extends CrypterException {
	private static final long serialVersionUID = 1L;

	public DecryptionFailedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
