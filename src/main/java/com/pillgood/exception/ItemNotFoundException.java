package com.pillgood.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotFoundException extends RuntimeException {
	private Object data;

	public ItemNotFoundException(String msg) {
		super(msg);
	}
	
}
