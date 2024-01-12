package com.utilities;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onTestStart(ITestResult testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/reports/myreports.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Employee API test Report");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Ashwini");
		
	}
	
	public void onTestSuccess(ITestResult testContext) {
		test = extent.createTest(testContext.getName());
		test.log(Status.PASS, "Testcase passed is: " + testContext.getName());
	}
	
	public void onTestFailure(ITestResult testContext) {
		test = extent.createTest(testContext.getName());
		test.log(Status.FAIL, "Testcase failed is: " + testContext.getName());
	}
	
	public void onTestSkipped(ITestResult testContext) {
		test = extent.createTest(testContext.getName());
		test.log(Status.SKIP, "Testcase skipped is: " + testContext.getName());
	}
	
	public void onFinish(ITestResult testContext) {
		extent.flush();
	}
}
