package com.excilys.formation.java.computerdatabase.exception;

/**
 * The Class MappingException.
 */
public class MappingException extends RuntimeException {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 9028537190885935470L;

	/**
	 * Instantiates a new mapping exception.
	 */
	public MappingException() {
		super();
	}

	/**
	 * Instantiates a new mapping exception.
	 *
	 * @param s the s
	 */
	public MappingException(String s) {
		super(s);
	}

	/**
	 * Instantiates a new mapping exception.
	 *
	 * @param s the s
	 * @param e the e
	 */
	public MappingException(String s, Exception e) {
		super(s, e);
	}
}
