package com.pillgood.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordMismatchException extends RuntimeException {
	
	private Object data;
	
	public PasswordMismatchException(String msg) {
		super(msg);
	}

}
