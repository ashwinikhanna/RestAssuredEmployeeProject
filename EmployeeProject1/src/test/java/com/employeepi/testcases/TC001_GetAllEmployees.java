package com.employeepi.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;

public class TC001_GetAllEmployees extends TestBase {
	
	@BeforeClass
	void preRequisiteGetAllEmployees() throws InterruptedException {
		logger.info("***** Setting up GetAllEmployees API ******");
		
		RestAssured.baseURI="https://dummy.restapiexample.com";
		RestAssured.basePath="/api/v1/employees";
		
		httpRequest = RestAssured.given();
		response = httpRequest.get();
		Thread.sleep(wait_time);
		response.then().log().headers();
		logger.info("***** GetAllEmployees API setup done ******");
	}
	
	@BeforeMethod
	void waitMethod() throws InterruptedException {
		//just put a 500ms wait after each method to avoid throwing exception
		Thread.sleep(wait_time);
	}
	
	@Test(priority=1)
	void checkResponseBody() {
		logger.info("***** Checking GetAllEmployees API ResponseBody ******");
		
		String responseBody = response.getBody().asString();
		logger.info("Response body is => " + responseBody);
		assertTrue(responseBody!=null);
		
		logger.info("***** ResponseBody check Done ******");
	}
	
	@Test(priority=2)
	void checkStatusCode() {
		logger.info("***** Checking GetAllEmployees API Statuscode ******");
		int actualStatusCode = response.getStatusCode();
		logger.info("Status code returned is: " + actualStatusCode);
		assertEquals(actualStatusCode, 200);
		
		logger.info("***** Statuscode check Done******");
	}
	
	@Test(priority=3)
	void checkResponseTime() {
		logger.info("***** Checking GetAllEmployees API ResponseTime ******");
		
		double responseTime = response.getTime();
		logger.info("ResponseTime is: "+ responseTime);
		
		if(responseTime > 3500) {
			logger.warn("Response time is:" + responseTime + "m/s, it should be under 2500 m/s");
		}
		assertTrue(responseTime<3500);
		
		logger.info("***** ResponseTime check Done ******");
	}
	
	@Test(priority=4)
	void checkStatusLine() {
		logger.info("***** Checking GetAllEmployees API StatusLine ******");
		
		String actualStatusLine = response.getStatusLine();
		logger.info("Status line is: " + actualStatusLine);
		assertEquals(actualStatusLine, "HTTP/1.1 200 OK");
		
		logger.info("***** StatusLine check Done ******");
	}
	
	@Test(priority=5)
	void checkServerType() {
		logger.info("***** Checking GetAllEmployees API ServerType ******");
		
		String serverType = response.header("Server");
		logger.info("Server line is: " + serverType);
		assertEquals(serverType, "nginx/1.21.6");
		
		logger.info("***** ServerType check Done ******");
	}
	
	@Test(priority=6)
	void checkContentEncoding() {
		logger.info("***** Checking GetAllEmployees API ContentEncoding ******");
		
		String ContentEncoding = response.header("Content-Encoding");
		logger.info("Content-Encoding line is: " + ContentEncoding);
		assertEquals(ContentEncoding, "gzip");
		
		logger.info("***** ContentEncoding check Done ******");
	}
	
	@Test(priority=7)
	void checkContentType() {
		logger.info("***** Checking GetAllEmployees API ContentType ******");
		
		String ContentType = response.header("Content-Type");
		logger.info("ContentType is: " + ContentType);
		assertEquals(ContentType, "application/json");
		
		logger.info("***** ContentType check Done ******");
	}
	
	@AfterClass
	void teardown() {
		response=null;
		logger = null;
		httpRequest=null;
		
		logger.info("***** GetAllEmployees API testcases done ******");
	}
}
