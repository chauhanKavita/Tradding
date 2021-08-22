package com.tradding.tradeflow.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tradding.tradeflow.model.Trade;
import com.tradding.tradeflow.repository.TradeRepository;
//It is a scheduler which runs at 01:15 am everyday and checks if any maturity dates have reached today's date and updates them as expired.
@Service
public class SchedulerService {
	
	@Autowired
	TradeRepository tradeRepository;
	
	@Scheduled(cron = "0 15 01 * * *")
	public void scheduledMethod() throws ParseException {
		List<Trade> trade= tradeRepository.findAll();
		for(Trade t:trade){
		Date maturity=new SimpleDateFormat("dd/MM/yyyy").parse(t.getMaturityDate()); 
		if(maturity.compareTo(new java.util.Date())<0) {
			t.setExpired("Y");
			tradeRepository.save(t);
		}
		}
	}
}
