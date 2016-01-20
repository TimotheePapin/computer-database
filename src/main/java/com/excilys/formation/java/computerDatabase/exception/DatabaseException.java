package com.excilys.formation.java.computerDatabase.exception;

/**
 * The Class DatabaseException.
 */
public class DatabaseException extends RuntimeException {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -97145057475664072L;

	/**
	 * Instantiates a new database exception.
	 */
	public DatabaseException() {
		super();
	}

	/**
	 * Instantiates a new database exception.
	 *
	 * @param s the s
	 */
	public DatabaseException(String s) {
		super(s);
	}

	/**
	 * Instantiates a new database exception.
	 *
	 * @param s the s
	 * @param e the e
	 */
	public DatabaseException(String s, Exception e) {
		super(s, e);
	}
}
