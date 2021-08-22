package com.tradding.tradeflow.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradding.tradeflow.exception.InvalidMaturityException;
import com.tradding.tradeflow.exception.InvalidValueException;
import com.tradding.tradeflow.model.Trade;
import com.tradding.tradeflow.services.TradeServices;

@RestController
public class TradeController {
	
	@Autowired 
	TradeServices tradeService; 
	
	//Controller for fetching all the trades from the database
	@GetMapping("/trade")
	private List<Trade> getAllTrades()   
	{  
	return tradeService.getAllTrades();  
	}  
	
	//Controller for storing the trades in the database
	@PostMapping(path="/trade", consumes="application/json") 
	private Trade saveStudent(@RequestBody Trade trade) throws InvalidValueException,InvalidMaturityException, ParseException 
	{  
		tradeService.saveOrUpdate(trade); 
		return trade;
}  
}  


