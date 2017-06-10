package com.secure.contracts;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

public interface KeyFile {

	public void serializeKey();

	public void serializeKey(OutputStream outputStream);

	public void setAlgorithm(String algoritm);

	public Key getKey();

	public void injectKey(String key);

	public void deserializeKey();

	public void setKeyFileName(String fileName);

	public void setFileInputStream(InputStream inputStream);

	public void setOutputStream(OutputStream stream);

	public void setFile(File keyFile);

}
