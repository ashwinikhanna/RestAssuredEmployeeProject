package com.employeepi.testcases;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.utilities.Utilities;

import io.restassured.RestAssured;

public class TC004_PutEmployee extends TestBase {

	String emp_name = Utilities.getName();
	String emp_age = Utilities.getAge();
	String emp_salary = Utilities.getSalary();
	
	@BeforeClass
	public void prerequitistePostEmployee() throws InterruptedException {
		Thread.sleep(wait_time);
		logger.info("****** Setting up PutEmployee API request ******");
		
		RestAssured.baseURI="https://dummy.restapiexample.com";
		RestAssured.basePath="/api/v1/update/" + employee_id;
		Thread.sleep(wait_time);
		
		JSONObject employee_obj = new JSONObject();
		employee_obj.put("employee_name", emp_name);
		employee_obj.put("employee_salary", emp_salary);
		employee_obj.put("employee_age", emp_age);
		
		httpRequest= RestAssured.given();
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(employee_obj.toString());
		Thread.sleep(wait_time);
		
		response = httpRequest.put();
		Thread.sleep(wait_time);
		
		logger.info("****** PutEmployee API request setup done******");
	}
	
	@BeforeMethod
	void waitMethod() throws InterruptedException {
		//just put a 500ms wait after each method to avoid throwing exception
		Thread.sleep(wait_time);
	}
	
	@Test(priority=1)
	void checkResponseCode() {
		logger.info("****** Checking  PutEmployee API StatusCode ******");
		
		assertEquals(response.getStatusCode(), 200);
		logger.info("****** PutEmployee API StatusCode check done ******");
		
		logger.info("****** Employee with id: " + employee_id + " updated ******");
		
	}
	
	@Test(priority=2)
	void logBody() {
		logger.info("****** Checking  PutEmployee API logBody ******");
		
		response.then().log().body();
		
		logger.info("****** PutEmployee API logBody done ******");
	}
	
	@Test(priority=3)
	void verifyRecordSuccessfullyUpdated() {
		logger.info("****** Checking  PutEmployee API verifyRecordSuccessfullyUpdated ******");
		
		String success_message1 = response.getBody().jsonPath().get("status");
		assertEquals(success_message1, "success");
		
		String success_message2 = response.getBody().jsonPath().get("message");
		assertEquals(success_message2, "Successfully! Record has been updated.");
		
		logger.info("****** PutEmployee API verifyRecordSuccessfullyUpdated done ******");
	}
	
	@AfterClass
	void teardown() {
		response=null;
		logger = null;
		httpRequest=null;
		
		logger.info("***** PutEmployee API testcases done ******");
	}
	
}
