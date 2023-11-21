package com.foxminded.senkiv.exceptions;

public class RaceApplicationRuntimeException extends RuntimeException {
	public RaceApplicationRuntimeException(String message){
		super(message);
	}

	public RaceApplicationRuntimeException(String message, Throwable cause){
		super(message, cause);
	}
}
