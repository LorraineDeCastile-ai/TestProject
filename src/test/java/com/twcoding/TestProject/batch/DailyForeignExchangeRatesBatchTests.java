package com.twcoding.TestProject.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twcoding.TestProject.batch.DailyForeignExchangeRatesBatch;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DailyForeignExchangeRatesBatchTests {
	
	@Autowired
	private DailyForeignExchangeRatesBatch dailyForeignExchangeRatesBatch;
	
	@Test
	public void syncDailyForeignExchangeRates() throws Exception {
		dailyForeignExchangeRatesBatch.syncDailyForeignExchangeRates();
	}
}
