package com.rishi.userbooking.customexception;

public class DuplicateEntryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 471417535353487508L;

	public DuplicateEntryException(String msg) {
		super(msg);
	}

}
