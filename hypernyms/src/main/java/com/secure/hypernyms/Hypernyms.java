package com.secure.hypernyms;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.secure.contracts.KeyFile;
import com.secure.contracts.Secure;
import com.secure.exception.KeyCreationException;
import com.secure.imple.KeyFileImple;
import com.secure.imple.SecureFile;

public class Hypernyms {

	private static Hypernyms hypernyms;
	private static final String KEY_ALGO = "AES";
	private static final String ALGO = "AES/ECB/PKCS5Padding";
	private static String KEY_FILE_NAME;
	private boolean isKeyFileCreated;

	private Secure secure;

	private String key;

	private Hypernyms() {
		if (secure == null)
			secure = new SecureFile();
		secure.setAlgorithm(ALGO);
		if (KEY_FILE_NAME != null)
			tryToFindKeyFile();
	}

	public void setKeyFileAbsolutePath(String absolutePath) {
		KEY_FILE_NAME = absolutePath;
		secure.setKeyFileName(KEY_FILE_NAME);
	}

	public void setFileInputStream(InputStream inputStream) {
		secure.setFileInputStream(inputStream);
	}

	public void setOutputStream(OutputStream stream) {
		secure.setOutputStream(stream);
	}

	public void setFile(File keyFile) {
		secure.setFile(keyFile);
	}

	public static Hypernyms getInstance() {
		if (hypernyms == null)
			hypernyms = new Hypernyms();
		return hypernyms;
	}

	private void tryToFindKeyFile() {
		File file = new File(KEY_FILE_NAME);
		if (file.exists())
			isKeyFileCreated = true;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void generateCipherKey() {
		genSecureKeyFile(null);
	}

	public void generateCipherKey(OutputStream outputStream) {
		genSecureKeyFile(outputStream);
	}

	private void genSecureKeyFile(OutputStream outputStream) {

		if (!isKeyFileCreated) {
			KeyFile keyFile = new KeyFileImple();
			keyFile.setAlgorithm(KEY_ALGO);
			keyFile.setKeyFileName(KEY_FILE_NAME);
			keyFile.injectKey(key);
			if (outputStream != null)
				keyFile.serializeKey(outputStream);
			else
				keyFile.serializeKey();
			isKeyFileCreated = true;
		}

	}

	public void encrypt(String fullQualifiedPathOfFileToBeEncrypted, String toFullQualifyiedPath) {
		isKeyGenerated();
		secure.encryptFile(fullQualifiedPathOfFileToBeEncrypted, toFullQualifyiedPath);

	}

	public void decrypt(String fullyQualifiedEncryptedFilePath, String tofullyQualifiedDecryptedFilePath) {
		isKeyGenerated();
		secure.encryptFile(fullyQualifiedEncryptedFilePath, tofullyQualifiedDecryptedFilePath);
	}

	public void isKeyGenerated() {
		if (!isKeyFileCreated) {
			new KeyCreationException("Before encryption/decryption key must be input at once for life time.");
		}
	}

	public Object getCipher() {
		return secure.getHypernmsCipher();
	}

}
