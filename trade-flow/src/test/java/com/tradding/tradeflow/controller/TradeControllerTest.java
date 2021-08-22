package com.tradding.tradeflow.controller;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradding.tradeflow.model.Trade;
import com.tradding.tradeflow.services.TradeServices;

@RunWith(SpringRunner.class)
@WebMvcTest(value=TradeController.class)
public class TradeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TradeServices tradeService;

	//Test for fetching all the trades from DB.
	@Test
	public void getAllTradesTest() throws Exception {
		
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
		
		Mockito.when(tradeService.getAllTrades()).thenReturn(mockTrade);
		String URI= "/trade";
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);
		
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(mockTrade);
		String outputInJson = result.getResponse().getContentAsString();

		Assert.assertEquals(outputInJson, expectedJson);
	}
	//Test for updating the trade in DB.
	@Test
	public void saveStudentTest() throws Exception {
		
		Trade t= new Trade();
		t.setTradeId("T1");
		t.setBookId("B3");
		t.setVersion(3);
		t.setCounterPartyId("CP-1");
		t.setCreatedDate("22/08/2021");
		t.setMaturityDate("22/08/2025");
		t.setExpired("Y");
		
		String inputInJson = this.mapToJson(t);
		
		Mockito.when(tradeService.saveOrUpdate(Mockito.any(Trade.class))).thenReturn(t);
		String URI= "/trade";
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				URI).accept(
				MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response =result.getResponse();

		String outputInJson = response.getContentAsString();

		Assert.assertEquals(outputInJson, inputInJson);
	}
	
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
