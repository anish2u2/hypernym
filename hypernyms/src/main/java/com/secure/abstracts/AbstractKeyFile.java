package com.secure.abstracts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.secure.contracts.KeyFile;

public abstract class AbstractKeyFile implements KeyFile, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 345456896998L;
	private ActualKey key;
	protected String keyFileName;
	private String privateKey;
	private OutputStream outputStream;

	public void setOutputStream(OutputStream stream) {
		this.outputStream = stream;
	}

	protected void setkey(Key key) {
		this.key = (ActualKey) key;
	}

	public void injectKey(String key) {
		this.privateKey = key;
	}

	public void serializeKey() {
		try {
			// System.out.print("Please Input a key:");
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(System.in));
			// String key = reader.readLine();
			System.out.println("$$$ key:" + privateKey);
			byte[] encodedKey = Base64.encodeBase64(privateKey.getBytes());
			System.out.println("Afterencoding :" + String.valueOf(encodedKey));
			this.key = new ActualKey(encodedKey, getAlgorithm());
			try (ObjectOutputStream objectOutPutStream = new ObjectOutputStream(
					outputStream == null ? (new FileOutputStream(new File(keyFileName))) : outputStream);) {
				objectOutPutStream.writeObject(this);
				objectOutPutStream.flush();
				privateKey = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void serializeKey(OutputStream outputStream) {
		try {
			// System.out.print("Please Input a key:");
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(System.in));
			// String key = reader.readLine();
			System.out.println("$$$ key:" + privateKey);
			byte[] encodedKey = Base64.encodeBase64(privateKey.getBytes());
			System.out.println("Afterencoding :" + String.valueOf(encodedKey));
			try {
				this.key = new ActualKey(encodedKey, getAlgorithm());
				ObjectOutputStream objectOutPutStream = new ObjectOutputStream(outputStream);
				objectOutPutStream.writeObject(this);
				objectOutPutStream.flush();
				objectOutPutStream.close();
				privateKey = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public Key getKey() {
		return key;
	}

	@SuppressWarnings("serial")
	private class ActualKey extends SecretKeySpec {

		public ActualKey(byte[] arg0, int arg1, int arg2, String arg3) {
			super(arg0, arg1, arg2, arg3);

		}

		public ActualKey(byte[] key, String algorithm) {
			super(key, algorithm);
		}

	}

	protected abstract String getAlgorithm();

}
