package com.twcoding.TestProject.batch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.twcoding.TestProject.dao.DailyForeignExchangeRatesRepository;
import com.twcoding.TestProject.entity.DailyForeignExchangeRatesEntity;

@Configuration
@EnableScheduling
public class DailyForeignExchangeRatesBatch {
	
	@Autowired
	private DailyForeignExchangeRatesRepository dailyForeignExchangeRatesRepository;

	@Scheduled(cron = "0 0 18 * * ?")
	public void syncDailyForeignExchangeRates(){
		List<Map<String, String>> data = sendHttpRequest();
		updateDailyForeignExchangeRatesByData(data);
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, String>> sendHttpRequest(){
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
		
		//添加轉換器，否則用Object接File會有問題
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM}));
		restTemplate.setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));
		
		List<Map<String, String>> responseJson = restTemplate.getForObject(url, List.class);
		return responseJson;
	}
	
	private void updateDailyForeignExchangeRatesByData(List<Map<String, String>> data){
		List<DailyForeignExchangeRatesEntity> dataList = dailyForeignExchangeRatesRepository.findAll();
		data.forEach(map->{
			DailyForeignExchangeRatesEntity entity = new DailyForeignExchangeRatesEntity();
			map.keySet().forEach(key->{
				if("Date".equalsIgnoreCase(key))
					entity.setDate(map.get(key));
				else if(key.length() > 0 && key.indexOf("/") > -1){
					entity.setCurrencyType(key.split("/")[0]);
					entity.setExchangeCurrencyType(key.split("/")[1]);
					entity.setExchangeRate(Double.valueOf(map.get(key)));
					entity.setID(null);
					saveIfNotExists(entity, dataList);
				}
			});
		});
	}
	
	private void saveIfNotExists(DailyForeignExchangeRatesEntity entity, List<DailyForeignExchangeRatesEntity> dataList){
		if(dataList.indexOf(entity) < 0)
			dailyForeignExchangeRatesRepository.saveAndFlush(entity);
	}
}
