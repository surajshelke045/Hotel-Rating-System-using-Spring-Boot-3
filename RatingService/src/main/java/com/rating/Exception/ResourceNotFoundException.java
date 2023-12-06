package com.rating.Exception;

public class ResourceNotFoundException  extends RuntimeException{

	public ResourceNotFoundException() {
		super("Resourcr not found on server !!");
	}
	
	public ResourceNotFoundException(String mssage) {
		
		super(mssage);
	}
}
