package com.secure.imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.secure.abstracts.AbstractKeyFile;

public class KeyFileImple extends AbstractKeyFile {

	private static final long serialVersionUID = 1L;

	private String algorithm;

	private File keyFile;

	public void setFile(File keyFile) {
		this.keyFile = keyFile;
	}

	private InputStream inputStream;

	public void setFileInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	protected String getAlgorithm() {

		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;

	}

	public void deserializeKey() {
		try {
			System.out.println("File Name:" + keyFileName);
			if (inputStream != null)
				System.out.println("is Input stream is not null..");
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream == null
					? (new FileInputStream(keyFile != null ? keyFile : new File(keyFileName))) : inputStream);
			AbstractKeyFile keyObject = (KeyFileImple) objectInputStream.readObject();
			this.setkey(keyObject.getKey());
			objectInputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void setKeyFileName(String fileName) {
		this.keyFileName = fileName;

	}

}
