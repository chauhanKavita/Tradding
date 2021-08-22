package com.tradding.tradeflow.exception;

public class InvalidValueException extends Exception{
	//Exception to be thrown when version is lower than the version in the database for any partivular trade.
		   public String toString(){ 
			return ("Your Trade is rejected. Kindly choose a higher version") ;
		   }
		
}
