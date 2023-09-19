package portunus.util.crypter;

public interface ICrypter {
	public String encrypt(String plainTextToEncrypt, String plainKey) throws EncryptionFailedException;
	public String decrypt(String encryptedTextToDecrypt, String plainKey) throws DecryptionFailedException;
}
