package com.user.Exception;

public class ResourceNotFoundException  extends RuntimeException{

	public ResourceNotFoundException() {
		super("Resource not found on server !!");
	}
	
	public ResourceNotFoundException(String mssage) {
		
		super(mssage);
	}
}
