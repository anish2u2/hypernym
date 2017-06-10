package com.secure.imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.CipherInputStream;

import org.apache.http.entity.mime.content.FileBody;

import com.secure.contracts.HypernymsCipher.Cipher;

public class HypernymsFileBody extends FileBody {

	private Cipher cipher;

	private File file;

	private InputStream inputStream;

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setCipher(Cipher cipher) {
		this.cipher = cipher;
	}

	public HypernymsFileBody(File file) {
		super(file);
		this.file = file;
	}

	@Override
	public void writeTo(final OutputStream outputStream) throws IOException {
		try {

			CipherInputStream inputStream = cipher
					.getEncryptedStream(this.inputStream == null ? new FileInputStream(file) : this.inputStream);
			byte[] buffer = new byte[(int) file.length()];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void decryptFileBodyAndWriteFile(String fileName, OutputStream fileOutputStream,
			InputStream networkInputStream) {
		try {
			byte[] buffer = new byte[4096];
			int length;
			if (fileOutputStream == null) {
				fileOutputStream = new FileOutputStream(new File(fileName));
			}
			CipherInputStream cipherInputStream = cipher.getDecryptedStream(networkInputStream);
			while ((length = cipherInputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, length);
			}
			fileOutputStream.flush();
			cipherInputStream.close();
			fileOutputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
