package com.employeepi.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;

public class TC002_GetSingleEmployee extends TestBase {

	@BeforeClass
	void prerequisiteGetSingleEmployee() throws InterruptedException {
		
		logger.info("***** Setting up GetSingleEmployee API ******");
		employee_id="4";
		RestAssured.baseURI="https://dummy.restapiexample.com";
		RestAssured.basePath="/api/v1/employee/" + employee_id;
		
		httpRequest =  RestAssured.given();
		response= httpRequest.get();
		Thread.sleep(wait_time);
		response.then().log().headers();
		
		logger.info("***** GetSingleEmployee API setup done ******");
	}
	
	@BeforeMethod
	void waitMethod() throws InterruptedException {
		//just put a 500ms wait after each method to avoid throwing exception
		Thread.sleep(wait_time);
	}
	
	@Test(priority=1)
	void checkResponseBody() {
		
		logger.info("***** Checking response body ******");
		String response_body = response.getBody().asString();
		response.then().log().body();
		
		assertTrue(response_body.contains(employee_id));
		logger.info("*****  response body check complete******");
	}
	
	@Test(priority=2)
	void checkStatusCode() {
		logger.info("***** Checking Status code ******");
		int status_code = response.getStatusCode();
		assertEquals(status_code, 200);
		logger.info("*****  Status code check done ******");
	}
	
	@Test(priority=3)
	void checkResponseTime() {
		logger.info("***** Checking response time ******");
		double response_time = response.getTime();
		logger.info("Response time is: " + response_time/1000);
		assertTrue(response_time<=3500);
		logger.info("***** Response time check done ******");
	}
	
	@AfterClass
	void teardown() {
		response=null;
		logger = null;
		httpRequest=null;
		
		logger.info("***** GetSingleEmployee API testcases done ******");
	}
}
