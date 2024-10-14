package com.pillgood.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartNotFoundException extends RuntimeException  {
	private Object data;

	public CartNotFoundException(String msg) {
		super(msg);
	}
	
}
