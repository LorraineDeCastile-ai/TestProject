package com.twcoding.TestProject.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.twcoding.TestProject.bean.Result;
import com.twcoding.TestProject.bean.ResultCode;
import com.twcoding.TestProject.service.DailyForeignExchangeRatesService;


@RestController
public class ApiController {
	
	@Autowired
	private DailyForeignExchangeRatesService deForeignExchangeRatesService;
	
	private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat QUERY_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	
	class DailyForeignExchangeRatesResult extends Result{
		private static final long serialVersionUID = -1867174874173555133L;
		
		List<Map<String, String>> currency;

		public List<Map<String, String>> getCurrency() {
			return currency;
		}

		public void setCurrency(List<Map<String, String>> currency) {
			this.currency = currency;
		}
		
	}
	
	@PostMapping("/forex")
	public String forexAPI(@RequestBody(required=true) String startDate, @RequestBody(required=true) String endDate, @RequestBody(required=true) String currency){
		DailyForeignExchangeRatesResult result = new DailyForeignExchangeRatesResult();
		result.setError(new ResultCode());
		
		//測試日期
		if(!checkStartDate(startDate) || !checkEndDate(endDate)) {
			result.getError().setCode("E001");
			result.getError().setMessage("日期區間不符");
			return new Gson().toJson(result);
		}else {
			result.getError().setCode("0000");
			result.getError().setMessage("成功");
		}

		startDate = QUERY_DATE_FORMAT.format(checkDate(startDate));
		endDate = QUERY_DATE_FORMAT.format(checkDate(endDate));
		
		result.setCurrency(deForeignExchangeRatesService.findByDateAndCurrencyType(startDate, endDate, currency));
		
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
		try {
			return INPUT_DATE_FORMAT.parse(date);
		}catch (Exception e) {
			return null;
		}
	}

}
