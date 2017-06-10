package com.secure.contracts;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public interface HypernymsCipher {

	public Cipher getCipher();

	public void setAlgorithm(String algoritm);

	public void setKeyFileName(String keyFileName);

	public void setFileInputStream(InputStream inputStream);

	public void setOutputStream(OutputStream stream);

	public interface Cipher {
		public CipherInputStream getEncryptedStream(Object file);

		public CipherInputStream getDecryptedStream(Object file);

		public CipherOutputStream getDecryptCipherOutputStream(Object file);

		public CipherOutputStream getEncryptedCipherOutputStream(Object file);

		public javax.crypto.Cipher getEncrypter();

		public javax.crypto.Cipher getDecrypter();

		public void _init(int mode);

	}

	public void setFile(File keyFile);

}
