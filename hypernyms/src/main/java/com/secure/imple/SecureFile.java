package com.secure.imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.CipherInputStream;

import com.secure.abstracts.AbstractSecure;

public class SecureFile extends AbstractSecure {

	public void encryptFile(String filePath, String toPath) {
		try {
			File file = new File(filePath);
			CipherInputStream fileInputStream = getCipher().getEncryptedStream(new FileInputStream(file));
			FileOutputStream outputStream = new FileOutputStream(new File(toPath));
			int read;
			byte[] buffer = new byte[(int) file.length()];
			while ((read = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
			}
			fileInputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void decryptFile(String filePath, String toPath) {
		try {
			File file = new File(filePath);
			CipherInputStream fileInputStream = getCipher().getDecryptedStream(new FileInputStream(file));
			FileOutputStream outputStream = new FileOutputStream(new File(toPath));
			int read;
			byte[] buffer = new byte[(int) file.length()];
			while ((read = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
			}

			outputStream.flush();
			outputStream.close();
			fileInputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	

}
