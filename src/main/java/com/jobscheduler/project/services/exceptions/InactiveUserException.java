package com.jobscheduler.project.services.exceptions;

public class InactiveUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InactiveUserException(String msg) {
		super(msg);
	}
}
