package com.sunqubit.faqture.beans.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class AESCipher {
	
	public static String encripta(String dato, String key) {
		String salt = KeyGenerators.string().generateKey();
		TextEncryptor encryptor = Encryptors.text(key, salt);
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString((salt + encryptor.encrypt(dato)).getBytes(StandardCharsets.UTF_8));
	}
	
	public static String desencripta(String dato, String key) {
		Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(dato);
		String salt = new String(decodedByteArray);
		TextEncryptor decryptor = Encryptors.text(key, salt.substring(0, 16));
		return decryptor.decrypt(salt.substring(16));
	}
}
