package com.tradding.tradeflow.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradding.tradeflow.exception.InvalidMaturityException;
import com.tradding.tradeflow.exception.InvalidValueException;
import com.tradding.tradeflow.model.Trade;
import com.tradding.tradeflow.repository.TradeRepository;

@Service
public class TradeServices {
	//Service that fetches all the trades from the database
	@Autowired
	TradeRepository tradeRepository;  
	public List<Trade> getAllTrades()   
	{  
		return tradeRepository.findAll();
	}  
	//Service to update the trade in the database
	public Trade saveOrUpdate(Trade trade) throws ParseException, InvalidValueException, InvalidMaturityException
	{  
		Date maturity=new SimpleDateFormat("dd/MM/yyyy").parse(trade.getMaturityDate()); 
		//Checking if maturity date is beyond today's date
		if(maturity.compareTo(new java.util.Date())>0) {
			List<Trade>	trades = tradeRepository.findByTradeId(trade.getTradeId());
			List<Integer> tradeVersion = new ArrayList<Integer>();
			for(Trade t:trades) {
				tradeVersion.add(t.getVersion());
			}
			//Checking if the version of the trade entered is always higher than the version already stored in the database.
			if(tradeVersion.isEmpty()) {
				tradeRepository.save(trade);  
				return tradeRepository.getByTradeIdAndVersion(trade.getTradeId(), trade.getVersion());
			}
			//If version is lower. throw exception
			else {
				List<Integer> sorted = tradeVersion.stream().sorted().collect(Collectors.toList());
				if(sorted.get(0)>trade.getVersion()) {
					throw new InvalidValueException();
				}
				else tradeRepository.save(trade); 
				return tradeRepository.getByTradeIdAndVersion(trade.getTradeId(), trade.getVersion());
			}
		}
		//If the maturity date is before today's date. throw exception
		else throw new InvalidMaturityException();
		
	}
} 


