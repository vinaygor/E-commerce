package com.my.spring.exception;

public class AdminException extends Exception {

	public AdminException(String message)
	{
		super("AdvertException-"+ message);
	}
	
	public AdminException(String message, Throwable cause)
	{
		super("AdvertException-"+ message,cause);
	}
}
