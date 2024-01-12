package com.employeepi.testcases;

import static org.testng.Assert.assertEquals;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;

public class TC005_DeleteEmployee extends TestBase {

	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		
		logger.info("****** Setting up deleteEmployee API requeit"
				+ "st ******");
		Thread.sleep(wait_time);
		
		//for delete
		logger.info("****** Preparing Employee for deletion in deleteEmployee API request ******");
		RestAssured.baseURI="https://dummy.restapiexample.com/public/api/v1";
		RestAssured.basePath="/delete/" + employee_id;
		Thread.sleep(wait_time);
		
		httpRequest = RestAssured.given();
		response = httpRequest.delete();
		Thread.sleep(wait_time);
		logger.info("****** Employee with id: " + employee_id + " deleted ******");
		
		logger.info("******deleteEmployee API request setup done ******");
	}
	
	@BeforeMethod
	void waitMethod() throws InterruptedException {
		//just put a 500ms wait after each method to avoid throwing exception
		Thread.sleep(wait_time);
	}
	
	@Test(priority=1)
	void checkResponse() throws InterruptedException {
		logger.info("****** checkResponse started ******");
		response.then().log().body();
		waitMethod();
		String message = response.jsonPath().getString("message");
		assertEquals(message, "Successfully! Record has been deleted");
		logger.info("****** checkResponse completed ******");
	}
	
	@Test(priority=2)
	void checkStatusCode() {
		logger.info("****** checkStatusCode started ******");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		logger.info("****** checkStatusCode completed ******");
	}
	
	@AfterClass
	void teardown() {
		response=null;
		logger = null;
		httpRequest=null;
		
		logger.info("***** DeleteEmployee API testcases done ******");
	}
	
}
