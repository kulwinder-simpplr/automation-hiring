package com.selenium.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.selenium.page.BaseTest;

public class SalesForce extends BaseTest {
	
	public void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
	}
	
	@Test
	public void launchSalesforce() {
		System.out.println("Launching!");
	}
	
}