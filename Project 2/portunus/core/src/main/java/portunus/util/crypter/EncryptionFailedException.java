package portunus.util.crypter;

public class EncryptionFailedException extends CrypterException {
	private static final long serialVersionUID = 1L;

	public EncryptionFailedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
