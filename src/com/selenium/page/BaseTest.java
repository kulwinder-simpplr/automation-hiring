package com.selenium.page;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public WebDriver driver;
	private String url = "NO_URL";
	private String cred = "NO_CRED";

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
		driver = new ChromeDriver(chromeoptions());
		driver.manage().window().maximize();
		System.out.println("Routing to : " + url);
		loginIntoSF();
		waitForElementPresence("//h1");
	}

	@AfterMethod
	public void tearDown() {
		// driver.quit();
	}

	public ChromeOptions chromeoptions() {
		try {
			ChromeOptions Chrome_Options = new ChromeOptions();
			Chrome_Options.addArguments("start-maximized");
			Chrome_Options.addArguments("disable-infobars");
			Chrome_Options.addArguments("enable-automation");
			Chrome_Options.addArguments("--no-sandbox");
			Chrome_Options.addArguments("'--disable-web-security'");
			Chrome_Options.addArguments("--allow-running-insecure-content");

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("profile.default_content_setting_values.notifications", 2);
			Chrome_Options.setExperimentalOption("prefs", chromePrefs);

			return Chrome_Options;
		} catch (Exception e) {
			System.err.println("Issue in chrome options " + e.getLocalizedMessage());
			return null;
		}
	}

	public void waitForElementPresence(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60, 200);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			System.err.println("Issue in wait element presence- " + e.getLocalizedMessage());
		}
	}
	
	public void loginIntoSF() {
		try {
			String txtbox_username = "//*[@id='username']";
			String txtbox_password = "//*[@id='password']";
			String btn_login = "//*[@id='Login']";
			driver.get(url);
			waitForElementPresence(txtbox_username);
			driver.findElement(By.xpath(txtbox_username)).sendKeys(cred.split("%")[0]);
			driver.findElement(By.xpath(txtbox_password)).sendKeys(cred.split("%")[1]);
			driver.findElement(By.xpath(btn_login)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForHomeURL() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60, 200);
			wait.until(ExpectedConditions.urlContains("home"));
		} catch (Exception e) {
			System.err.println("Issue in wait page title- " + e.getLocalizedMessage());
		}
	}

}
