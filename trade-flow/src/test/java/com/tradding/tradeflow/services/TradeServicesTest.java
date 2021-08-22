package com.tradding.tradeflow.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.tradding.tradeflow.exception.InvalidMaturityException;
import com.tradding.tradeflow.exception.InvalidValueException;
import com.tradding.tradeflow.model.Trade;
import com.tradding.tradeflow.repository.TradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServicesTest {
	
	@MockBean
	private TradeRepository tradeRepository;
	@Autowired
	private TradeServices tradeService;
	
	//Test for fetching all the trades from DB.
	@Test
	public void getAllTrades() throws Exception{
		Trade t= new Trade();
		t.setTradeId("T1");
		t.setBookId("B3");
		t.setVersion(3);
		t.setCounterPartyId("CP-1");
		t.setCreatedDate("22/08/2021");
		t.setMaturityDate("22/08/2025");
		t.setExpired("Y");
		List<Trade> mockTrade = new ArrayList<>();
		mockTrade.add(t);
		
		List<Trade> actualTrade = tradeRepository.findAll();
		Mockito.when(tradeRepository.findAll()).thenReturn(mockTrade);
		Assert.assertEquals(tradeService.getAllTrades(), mockTrade);

		
	}
	
	//Test for Correct Maturity and Version
	@Test
	public void saveOrUpdateSuccessTest() throws Exception {
		
		Trade t= new Trade();
		t.setTradeId("T1");
		t.setBookId("B3");
		t.setVersion(3);
		t.setCounterPartyId("CP-1");
		t.setCreatedDate("22/08/2021");
		t.setMaturityDate("22/08/2025");
		t.setExpired("Y");

		Mockito.when(tradeRepository.getByTradeIdAndVersion(t.getTradeId(),t.getVersion())).thenReturn(t);
		Assert.assertEquals(tradeService.saveOrUpdate(t), t);
	}
	
	//Test for checking expired maturity date.
	@Test
	public void saveOrUpdateIncorrectMaturityTest() throws Exception {
		
		Trade t= new Trade();
		t.setTradeId("T1");
		t.setBookId("B3");
		t.setVersion(3);
		t.setCounterPartyId("CP-1");
		t.setCreatedDate("22/08/2021");
		t.setMaturityDate("22/07/2021");
		t.setExpired("Y");

		boolean thrown = false;

		  try {
			  tradeService.saveOrUpdate(t);
		  } catch (InvalidMaturityException e) {
		    thrown = true;
		  }

		  Assert.assertTrue(thrown);

	}
	
	//Test for checking smaller version.
	@Test
	public void saveOrUpdateSmallerVersionTest() throws Exception {
		
		Trade tInsert= new Trade();
		tInsert.setTradeId("T2");
		tInsert.setBookId("B3");
		tInsert.setVersion(1);
		tInsert.setCounterPartyId("CP-1");
		tInsert.setCreatedDate("22/08/2021");
		tInsert.setMaturityDate("22/07/2028");
		tInsert.setExpired("Y");
		
		List<Trade> list = new ArrayList<Trade>();
		Trade t1= new Trade();
		t1.setTradeId("T2");
		t1.setBookId("B3");
		t1.setVersion(3);
		t1.setCounterPartyId("CP-1");
		t1.setCreatedDate("22/08/2028");
		t1.setMaturityDate("22/07/2028");
		t1.setExpired("Y");
		list.add(t1);
		Trade t2= new Trade();
		t2.setTradeId("T2");
		t2.setBookId("B3");
		t2.setVersion(2);
		t2.setCounterPartyId("CP-1");
		t2.setCreatedDate("22/08/2028");
		t2.setMaturityDate("22/07/2028");
		t2.setExpired("Y");
		list.add(t2);

		Mockito.when(tradeRepository.findByTradeId(tInsert.getTradeId())).thenReturn(list);
		boolean thrown = false;

		  try {
			  tradeService.saveOrUpdate(tInsert);
		  } catch (InvalidValueException e) {
		    thrown = true;
		  }

		  Assert.assertTrue(thrown);

	}
	

}
