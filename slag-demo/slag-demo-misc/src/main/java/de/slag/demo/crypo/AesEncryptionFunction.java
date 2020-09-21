package de.slag.demo.crypo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.function.Function;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptionFunction implements Function<String, String> {

	private final SecretKeySpec secretKey;

	public AesEncryptionFunction(String keyString) {
		MessageDigest sha = null;
		byte[] tmpKey;
		try {
			tmpKey = keyString.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			tmpKey = sha.digest(tmpKey);
			tmpKey = Arrays.copyOf(tmpKey, 16);
			secretKey = new SecretKeySpec(tmpKey, "AES");
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String apply(String encryptee) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(encryptee.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
