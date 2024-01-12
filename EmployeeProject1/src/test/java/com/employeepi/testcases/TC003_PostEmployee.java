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

public class TC003_PostEmployee extends TestBase {

	String emp_name = Utilities.getName();
	String emp_age = Utilities.getAge();
	String emp_salary = Utilities.getSalary();
	
	@BeforeClass
	public void prerequitistePostEmployee() throws InterruptedException {
		
		Thread.sleep(wait_time);
		logger.info("****** Setting up PostEmployee API request ******");
		RestAssured.baseURI="https://dummy.restapiexample.com";
		RestAssured.basePath="/api/v1/create";
		
		JSONObject employee_obj = new JSONObject();
		employee_obj.put("employee_name", emp_name);
		employee_obj.put("employee_salary", emp_salary);
		employee_obj.put("employee_age", emp_age);
		
		httpRequest= RestAssured.given();
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(employee_obj.toString());
		
		response = httpRequest.post();
		Thread.sleep(wait_time);
		
		logger.info("****** PostEmployee API request setup done******");
	}
	
	@BeforeMethod
	void waitMethod() throws InterruptedException {
		//just put a 500ms wait after each method to avoid throwing exception
		Thread.sleep(wait_time);
	}
	
	@Test(priority=1)
	void checkResponseCode() {
		logger.info("****** Checking  PostEmployee API StatusCode ******");
		assertEquals(response.getStatusCode(), 200);
		logger.info("****** PostEmployee API StatusCode check done ******");
	}
	
	@Test(priority=2)
	void logAll() {
		logger.info("****** Checking  PostEmployee API logAll ******");
		response.then().log().all();
		logger.info("****** PostEmployee API logAll done ******");
		
		logger.info("****** Get Employee ID created ******");
		employee_id = response.getBody().jsonPath().get("data.id").toString();
		logger.info("Employee with id: " + employee_id + " is created");
		logger.info("****** Employee ID grabbed  ******");

	}
	
	@Test(priority=3)
	void verifyRecordSuccessfullyAdded() {
		logger.info("****** Checking  PostEmployee API verifyRecordSuccessfullyAdded ******");
		
		String success_message = response.getBody().jsonPath().get("message");
		assertEquals(success_message, "Successfully! Record has been added.");
		
		logger.info("****** PostEmployee API verifyRecordSuccessfullyAdded done ******");
	}
	
	@AfterClass
	void teardown() {
		response=null;
		logger = null;
		httpRequest=null;
		
		logger.info("***** PostEmployee API testcases done ******");
	}
	
}
