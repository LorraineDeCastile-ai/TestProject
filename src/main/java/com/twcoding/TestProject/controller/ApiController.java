package com.twcoding.TestProject.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.twcoding.TestProject.dao.DailyForeignExchangeRatesRepository;
import com.twcoding.TestProject.entity.DailyForeignExchangeRatesEntity;


@RestController
public class ApiController {

	@Autowired
	private DailyForeignExchangeRatesRepository dailyForeignExchangeRatesRepository;
	
	@PostMapping("/")
	public String forexAPI(@RequestHeader(value="startDate", required=true) String startDate, @RequestHeader(value="endDate", required=true) String endDate, @RequestHeader(value="currency", required=true) String currency){
		Map<String, Object> result = new LinkedHashMap<>();
		Map<String, String> error = new LinkedHashMap<>();
		result.put("error", error);
		error.put("code", "E001");
		error.put("message", "日期區間不符");
		//測試日期
		if(!checkStartDate(startDate) || !checkEndDate(endDate))
			return new Gson().toJson(result);

		error.put("code", "0000");
		error.put("message", "成功");
		startDate = startDate.replaceAll("[-/]+", "");
		endDate = endDate.replaceAll("[-/]+", "");
		
		List<DailyForeignExchangeRatesEntity> list = dailyForeignExchangeRatesRepository.findByDateAndCurrencyType(startDate, endDate, currency);
		result.put("currency", list.stream().map(entity->{
			Map<String, String> map = new HashMap<>();
			map.put("date", entity.getDate());
			map.put(currency.toLowerCase(), entity.getExchangeRate().toString());
			return map;
		}).collect(Collectors.toList()));
		
		return new Gson().toJson(result);
	}

	/**
	 * 檢查日期格式
	 * 檢查開始日期最早為去年
	 * @param startDate
	 * @return
	 */
	private boolean checkStartDate(String startDate){
		Date start = checkDate(startDate);
		if(start != null){
			//比對去年時間
			Calendar lastYear = Calendar.getInstance();
			lastYear.add(Calendar.YEAR, -1);
			if(lastYear.getTimeInMillis() <= start.getTime())
				return true;
		}
		return false;
	}
	
	/**
	 * 檢查日期格式
	 * 檢查結束日期最晚為昨天
	 * @param endDate
	 * @return
	 */
	private boolean checkEndDate(String endDate){
		Date end = checkDate(endDate);
		if(checkDate(endDate) != null){
			Calendar lastDate = Calendar.getInstance();
			lastDate.add(Calendar.DATE, -1);
			System.out.println(lastDate.getTimeInMillis());
			System.out.println(end.getTime());
			if(lastDate.getTimeInMillis() >= end.getTime())
				return true;
		}
		return false;
	}
	
	/**
	 * 檢查日期格式
	 * @param date
	 * @return 無問題則返回Date
	 */
	private Date checkDate(String date){
		if(date.split("/").length != 3)
			return null;
		Date inputDate;
		try{
			int year = Integer.valueOf(date.split("/")[0]);
			int month = Integer.valueOf(date.split("/")[1]);
			int day = Integer.valueOf(date.split("/")[2]);
			if(month < 1 || month > 12)
				return null;
			if(day < 1 || day > 31)
				return null;
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, day);//月份從0~11
			inputDate = calendar.getTime();
		}catch (Exception e) {
			return null;
		}
		return inputDate;
	}
}
