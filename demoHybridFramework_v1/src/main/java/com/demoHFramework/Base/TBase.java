package com.demoHFramework.Base;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demoHFramework.pageObjects.login;

public class TBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger log = LogManager.getRootLogger();
	public static SimpleDateFormat dateFormat;
	public ExtentReports extentReports;
	public ExtentTest TestReport;
	public ExtentHtmlReporter ehtmlreporter;
	public login lg = new login(); 
	
	public TBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//Resources//ConfigFile//config.properties");
			prop.load(fis);
		}catch(IOException ioe) {
		log.error("Config Property File Exception"+ioe);	
		}
		
		dateFormat = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
		
	}
	
	@BeforeSuite
	public void LogSetup() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	@BeforeTest
	public void reportSetup() {
		ehtmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//Reports//ExtentReport-"+this.getClass().getSimpleName()+".html");
		ehtmlreporter.config().setDocumentTitle("Test Extent Report");
		ehtmlreporter.config().setReportName("Report Name");
		ehtmlreporter.config().setTheme(Theme.DARK);
		extentReports = new ExtentReports();
		extentReports.attachReporter(ehtmlreporter);
		extentReports.setSystemInfo("OS", "Windows 7");
		extentReports.setSystemInfo("Browser", "Chrome");
		
		
		
	}
	@BeforeMethod
	public void setup() {
		String browsername = prop.getProperty("browser");
		switch(browsername.toUpperCase()) {
		case "CHROME" : System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Resources//Drivers//chromedriver.exe");
						driver =  new ChromeDriver();
						log.info("Chrome Driver Is selected");
						break;
		case "IE" : System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Resources//Drivers//chromedriver.exe");
						driver =  new ChromeDriver();
						log.info("IE Driver Is selected");
						break;
		case "MORZILA" : System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Resources//Drivers//geckodriver.exe");
						DesiredCapabilities capabilities = DesiredCapabilities.firefox();
						capabilities.setCapability("marionette",true);
						driver= new FirefoxDriver(capabilities);
						log.info("FireFox Driver Is selected");
						break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		String url = prop.getProperty("url");
		log.info("URL : --> "+url);
		driver.get(url);	
		String className = this.getClass().getName();
		TestReport = extentReports.createTest(className);
	}
	
	

	
	
	@AfterMethod
	public void Exit() {
		driver.quit();
		
	}
	
	@AfterTest
	public void reportend() {
		extentReports.flush();
	}
	
	
}
