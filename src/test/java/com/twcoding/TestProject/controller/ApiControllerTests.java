package com.twcoding.TestProject.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApiControllerTests {
	
	@Autowired
	private ApiController apiController;
	
	@Test
	public void forexAPI1() throws Exception {
		String startDate = "2025/01/01";
		String endDate = "2025/02/13";
		String currency = "USD";
		String result = apiController.forexAPI(startDate, endDate, currency);
		Assert.assertTrue(result.indexOf("message\":\"成功") > -1);
	}
	
	@Test
	public void forexAPI2() throws Exception {
		String startDate = "2023/01/01";
		String endDate = "2025/02/13";
		String currency = "USD";
		String result = apiController.forexAPI(startDate, endDate, currency);
		Assert.assertFalse(result.indexOf("message\":\"成功") > -1);
	}
}
