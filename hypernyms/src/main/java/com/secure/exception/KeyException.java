package com.secure.exception;

public class KeyException extends Exception {

	/**
	 * @author Anish Singh
	 * 
	 *         This Exception will be thrown when the key length is shorter than
	 *         10.
	 */
	private static final long serialVersionUID = 1345345L;

	public KeyException(String exception) {
		super(exception);
	}

}
