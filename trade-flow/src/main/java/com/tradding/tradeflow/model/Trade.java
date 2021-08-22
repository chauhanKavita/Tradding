package com.tradding.tradeflow.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
//Main Trade class
@Entity
@IdClass(TradeId.class)
public class Trade implements Serializable {

@Id
 private String tradeId;
@Id
 private int version;
 private String counterPartyId;
 private String bookId;
 private String maturityDate;
 private String createdDate;
 private String expired;
public String getTradeId() {
	return tradeId;
}
public void setTradeId(String tradeId) {
	this.tradeId = tradeId;
}
public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}
public String getCounterPartyId() {
	return counterPartyId;
}
public void setCounterPartyId(String counterPartyId) {
	this.counterPartyId = counterPartyId;
}
public String getBookId() {
	return bookId;
}
public void setBookId(String bookId) {
	this.bookId = bookId;
}

public String getMaturityDate() {
	return maturityDate;
}
public void setMaturityDate(String maturityDate) {
	this.maturityDate = maturityDate;
}
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
public String getExpired() {
	return expired;
}
public void setExpired(String expired) {
	this.expired = expired;
}
@Override
public String toString() {
	return "Trade [tradeId=" + tradeId + ", version=" + version + ", counterPartyId=" + counterPartyId + ", bookId="
			+ bookId + ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired + "]";
}
 
 
}
