package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.chainsaw.Main;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public Logger logger;
	
	public static String employee_id;
	public int wait_time=2100;
	
	@BeforeClass
	public void setup() {
		logger = Logger.getLogger(Main.class.getName()); //added logger
		PropertyConfigurator.configure("log4j.properties"); //bind properties file
		logger.setLevel(Level.DEBUG); //set log level
		
	}
	
}
