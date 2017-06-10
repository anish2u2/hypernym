package com.secure.imple;

import java.io.InputStream;
import java.io.OutputStream;

import com.secure.abstracts.AbstractHypernymsCipher;

public class HypernymsCipherImple extends AbstractHypernymsCipher {

	private Cipher cipher;

	public Cipher getCipher() {
		if (cipher.getEncrypter() == null)
			cipher._init(javax.crypto.Cipher.ENCRYPT_MODE);
		if (cipher.getDecrypter() == null)
			cipher._init(javax.crypto.Cipher.DECRYPT_MODE);
		return cipher;
	}

	@Override
	protected void setCipherImple(Cipher cipher) {
		this.cipher = cipher;

	}

	public void setAlgorithm(String algoritm) {

		this.algorithm = algoritm;
	}

	public void setKeyFileName(String keyFileName) {
		this.keyFileName = keyFileName;

	}

	public void setFileInputStream(InputStream inputStream) {
		this.inputStream = inputStream;

	}

	public void setOutputStream(OutputStream stream) {
		this.outputStream = stream;
	}

}
