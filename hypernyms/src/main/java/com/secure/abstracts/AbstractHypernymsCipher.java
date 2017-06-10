package com.secure.abstracts;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import com.secure.contracts.HypernymsCipher;
import com.secure.contracts.KeyFile;
import com.secure.imple.KeyFileImple;

public abstract class AbstractHypernymsCipher implements HypernymsCipher {

	protected String algorithm;
	protected String keyFileName;
	protected InputStream inputStream;
	protected OutputStream outputStream;
	protected File keyFile;

	protected AbstractHypernymsCipher() {
		setCipherImple(new CipherImplementor());
	}

	public void setFile(File keyFile) {
		this.keyFile = keyFile;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	private class CipherImplementor implements Cipher {

		private javax.crypto.Cipher encryptor;

		private javax.crypto.Cipher decryptor;

		private void initCipher(CipherInitValues values) {
			try {
				if (values.getCipherMode() == javax.crypto.Cipher.ENCRYPT_MODE)
					encryptor = javax.crypto.Cipher.getInstance(getAlgorithm());
				else
					decryptor = javax.crypto.Cipher.getInstance(getAlgorithm());
				KeyFile key = new KeyFileImple();
				key.setAlgorithm(getAlgorithm());
				key.setKeyFileName(keyFileName);
				key.setFileInputStream(inputStream);
				key.setOutputStream(outputStream);
				key.setFile(keyFile);
				key.deserializeKey();
				(values.getCipherMode() == javax.crypto.Cipher.ENCRYPT_MODE ? encryptor : decryptor)
						.init(values.getCipherMode(), key.getKey());
				System.out.println("Cipher success fully created..");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void _init(int mode) {
			CipherInitValues cipherInitValues = new CipherInitValues();
			cipherInitValues.setCipherMode(mode);
			initCipher(cipherInitValues);
		}

		public CipherInputStream getEncryptedStream(Object inputStream) {
			if (encryptor == null)
				_init(javax.crypto.Cipher.ENCRYPT_MODE);
			CipherInputStream stream = null;
			try {
				System.out.println("Getting encrypted Cipher ..");
				stream = new CipherInputStream(((InputStream) inputStream), encryptor);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}

		public CipherInputStream getDecryptedStream(Object inputStream) {
			if (decryptor == null)
				_init(javax.crypto.Cipher.DECRYPT_MODE);
			CipherInputStream stream = null;
			try {
				stream = new CipherInputStream(((InputStream) inputStream), decryptor);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}

		public CipherOutputStream getDecryptCipherOutputStream(Object outputStream) {
			if (decryptor == null)
				_init(javax.crypto.Cipher.DECRYPT_MODE);
			CipherOutputStream stream = null;
			try {
				stream = new CipherOutputStream(((OutputStream) outputStream), decryptor);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}

		public CipherOutputStream getEncryptedCipherOutputStream(Object outputStream) {
			if (encryptor == null)
				_init(javax.crypto.Cipher.ENCRYPT_MODE);
			CipherOutputStream stream = null;
			try {
				stream = new CipherOutputStream(((OutputStream) outputStream), encryptor);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}

		public javax.crypto.Cipher getEncrypter() {
			return encryptor;
		}

		public javax.crypto.Cipher getDecrypter() {
			return decryptor;
		}
	}

	protected abstract void setCipherImple(Cipher cipher);

	protected class CipherInitValues {
		private int cipherMode;
		private String decryptingCharset;

		public int getCipherMode() {
			return cipherMode;
		}

		public void setCipherMode(int cipherMode) {
			this.cipherMode = cipherMode;
		}

		public String getDecryptingCharset() {
			return decryptingCharset;
		}

		public void setDecryptingCharset(String decryptingCharset) {
			this.decryptingCharset = decryptingCharset;
		}

	}

}
