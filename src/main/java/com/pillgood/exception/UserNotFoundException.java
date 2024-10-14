package com.pillgood.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
	private Object data;

	public UserNotFoundException(String msg) {
		super(msg);
	}
	
}
