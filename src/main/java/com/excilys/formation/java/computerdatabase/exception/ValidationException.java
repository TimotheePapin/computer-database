package com.excilys.formation.java.computerdatabase.exception;

/**
 * The Class ValidationException.
 */
public class ValidationException extends RuntimeException {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -955749037196054465L;

	/**
	 * Instantiates a new validation exception.
	 */
	public ValidationException() {
		super();
	}

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param s the s
	 */
	public ValidationException(String s) {
		super(s);
	}

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param s the s
	 * @param e the e
	 */
	public ValidationException(String s, Exception e) {
		super(s, e);
	}
}
