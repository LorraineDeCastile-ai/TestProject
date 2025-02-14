package com.twcoding.TestProject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DAILY_FOREIGN_EXCHANGE_RATES")
public class DailyForeignExchangeRatesEntity implements Serializable {

	private static final long serialVersionUID = 609455766381496820L;

	@Column(name = "DATE")
	private String date; //日期
	
	@Column(name = "CURRENCY_TYPE")
	private String currencyType; //貨幣種類
	
	@Column(name = "EXCHANGE_CURRENCY_TYPE")
	private String exchangeCurrencyType; //兌換幣種
	
	@Column(name = "EXCHANGE_RATE")
	private Double exchangeRate; //匯率
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getExchangeCurrencyType() {
		return exchangeCurrencyType;
	}

	public void setExchangeCurrencyType(String exchangeCurrencyType) {
		this.exchangeCurrencyType = exchangeCurrencyType;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
}
