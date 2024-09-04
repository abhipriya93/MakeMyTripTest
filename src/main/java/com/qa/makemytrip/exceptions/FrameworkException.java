package com.qa.makemytrip.exceptions;

public class FrameworkException extends Exception{

	public FrameworkException(String mesg)
	{
		super(mesg);
		printStackTrace();
	}
	
}
