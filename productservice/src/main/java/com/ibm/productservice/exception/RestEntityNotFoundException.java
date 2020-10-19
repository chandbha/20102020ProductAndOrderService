package com.ibm.productservice.exception;

public class RestEntityNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RestEntityNotFoundException(String message) {
		super(message);
	}

}
