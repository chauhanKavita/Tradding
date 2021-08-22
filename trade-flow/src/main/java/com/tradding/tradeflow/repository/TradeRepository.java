package com.tradding.tradeflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradding.tradeflow.model.Trade;
import com.tradding.tradeflow.model.TradeId;
//JPA repository
public interface TradeRepository extends JpaRepository<Trade, TradeId>{

	public List<Trade> findByTradeId(String tradeId);
	
	public Trade getByTradeIdAndVersion(String tradeId, int version);
}
