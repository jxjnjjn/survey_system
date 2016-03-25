/**
 * 
 */
package com.bisys.core.exception;

import org.springframework.core.NestedRuntimeException;


public class PermissionsException extends NestedRuntimeException {

	private static final long serialVersionUID = -6580390752625165276L;

	public PermissionsException(String message) {
		super(message);
	}
	
	public PermissionsException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
