package com.tradding.tradeflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidMaturityException extends Exception{
	//Exception to be thrown when Maturity date is less than today's date
	
	public String toString(){ 
		return ("Maturity date should not be less than today's date") ;
	   }
}
