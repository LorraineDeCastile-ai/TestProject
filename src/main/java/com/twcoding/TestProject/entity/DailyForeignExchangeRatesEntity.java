package com.twcoding.TestProject.entity;

import java.io.Serializable;
import java.util.Objects;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	@Column(name = "DATE")
	private String date; //日期
	
	@Column(name = "CURRENCY_TYPE")
	private String currencyType; //貨幣種類
	
	@Column(name = "EXCHANGE_CURRENCY_TYPE")
	private String exchangeCurrencyType; //兌換幣種
	
	@Column(name = "EXCHANGE_RATE")
	private Double exchangeRate; //匯率

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
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

	@Override
	public int hashCode() {
		return Objects.hash(currencyType, date, exchangeCurrencyType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyForeignExchangeRatesEntity other = (DailyForeignExchangeRatesEntity) obj;
		return Objects.equals(currencyType, other.currencyType) && Objects.equals(date, other.date)
				&& Objects.equals(exchangeCurrencyType, other.exchangeCurrencyType);
	}
	
}
