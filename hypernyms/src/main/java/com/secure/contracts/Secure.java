package com.secure.contracts;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface Secure {

	public void encryptFile(String filePath, String toPath);

	public void decryptFile(String filePath, String toPath);

	public void setAlgorithm(String algorithm);

	public void setKeyFileName(String keyFileName);

	public Object getHypernmsCipher();

	public void setFileInputStream(InputStream inputStream);

	public void setOutputStream(OutputStream stream);

	public void setFile(File keyFile);
}
