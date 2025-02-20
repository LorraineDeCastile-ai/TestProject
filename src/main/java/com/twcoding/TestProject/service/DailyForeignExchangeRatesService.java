package com.twcoding.TestProject.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twcoding.TestProject.dao.DailyForeignExchangeRatesRepository;
import com.twcoding.TestProject.entity.DailyForeignExchangeRatesEntity;

@Service
public class DailyForeignExchangeRatesService {
	
	@Autowired
	private DailyForeignExchangeRatesRepository dailyForeignExchangeRatesRepository;
	
	public List<Map<String, String>> findByDateAndCurrencyType(String startDate, String endDate, String currency) {

		List<DailyForeignExchangeRatesEntity> list = dailyForeignExchangeRatesRepository.findByDateAndCurrencyType(startDate, endDate, currency);
		return list.stream().map((entity)->{
			Map<String, String> map = new HashMap<>();
			map.put("date", entity.getDate());
			map.put(currency.toLowerCase(), entity.getExchangeRate().toString());
			return map;
		}).collect(Collectors.toList());
	}
}
