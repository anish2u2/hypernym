package com.secure.abstracts;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.secure.contracts.HypernymsCipher;
import com.secure.contracts.HypernymsCipher.Cipher;
import com.secure.contracts.Secure;
import com.secure.imple.HypernymsCipherImple;

public abstract class AbstractSecure implements Secure {

	private HypernymsCipher hypernymsCipher;

	public AbstractSecure() {
		hypernymsCipher = new HypernymsCipherImple();
	}

	public void setAlgorithm(String algorithm) {
		hypernymsCipher.setAlgorithm(algorithm);
	}

	public void setKeyFileName(String keyFileName) {
		hypernymsCipher.setKeyFileName(keyFileName);
	}

	protected Cipher getCipher() {
		return hypernymsCipher.getCipher();
	}

	public Object getHypernmsCipher() {
		return getCipher();
	}

	public void setFileInputStream(InputStream inputStream) {
		hypernymsCipher.setFileInputStream(inputStream);
	}

	public void setOutputStream(OutputStream stream) {
		hypernymsCipher.setOutputStream(stream);
	}

	public void setFile(File keyFile) {
		hypernymsCipher.setFile(keyFile);
	}
}
