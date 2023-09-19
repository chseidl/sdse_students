package portunus.util.crypter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypter implements ICrypter {

	protected SecretKeySpec createSecretKeySpec(String plainKey) {
		try {
			byte[] plainKeyBytes = plainKey.getBytes("UTF-8");
			
			//Hash the plain password
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] shaHashedKeyBytes = sha.digest(plainKeyBytes);
			
			//Bring hashed bytes to length expected by AES (16 bytes).
			byte[] truncatedKeyBytes = Arrays.copyOf(shaHashedKeyBytes, 16);
			
			//Create the AES secret key specification.
			return new SecretKeySpec(truncatedKeyBytes, "AES");
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			//Should not happen unless this is statically misconfigured.
			e.printStackTrace();
		}
		
		return null;
	}

	protected Cipher createCipher() {
		try {
			return Cipher.getInstance("AES/ECB/PKCS5Padding");
		} catch(NoSuchAlgorithmException | NoSuchPaddingException e) {
			//Should not happen unless this is statically misconfigured.
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String encrypt(String plainTextToEncrypt, String plainKey) throws EncryptionFailedException {
		try {
			SecretKeySpec secretKeySpec = createSecretKeySpec(plainKey);
			
			//Create cipher and set it to encryption mode.
			Cipher cipher = createCipher();
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			
			String decryptedDecodedString = plainTextToEncrypt;
			byte[] decryptedDecodedBytes = decryptedDecodedString.getBytes("UTF-8");
			byte[] encryptedDecodedBytes = cipher.doFinal(decryptedDecodedBytes);
			
			String encryptedEncodedString = Base64.getEncoder().encodeToString(encryptedDecodedBytes);
			
			return encryptedEncodedString;
		} catch (Exception e) {
			throw new EncryptionFailedException("Encryption failed.", e);
		}
	}

	@Override
	public String decrypt(String encryptedTextToDecrypt, String plainKey) throws DecryptionFailedException {
		try {
			SecretKeySpec secretKeySpec = createSecretKeySpec(plainKey);
			
			//Create cipher and set it to decryption mode.
			Cipher cipher = createCipher();
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			
			String encryptedEncodedString = encryptedTextToDecrypt;
			byte[] encryptedDecodedBytes = Base64.getDecoder().decode(encryptedEncodedString);
			byte[] decryptedDecodedBytes = cipher.doFinal(encryptedDecodedBytes);
			String decryptedDecodedString = new String(decryptedDecodedBytes);
			
			return decryptedDecodedString;
		} catch (Exception e) {
			throw new DecryptionFailedException("Decryption failed.", e);
		}
	}

	public static void main(String[] args) throws Exception {
		final String secretKey = "MyPassword";

		ICrypter crypter = new AESCrypter();
		
		String originalString = "Hello World";
		String encryptedString = crypter.encrypt(originalString, secretKey);
		String decryptedString = crypter.decrypt(encryptedString, secretKey);

		System.out.println(originalString);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
	}
}