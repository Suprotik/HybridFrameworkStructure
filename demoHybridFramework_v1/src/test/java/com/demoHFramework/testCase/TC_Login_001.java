package com.demoHFramework.testCase;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.demoHFramework.Base.TBase;
import com.demoHFramework.pageObjects.login;
import com.sun.media.jfxmedia.logging.Logger;

public class TC_Login_001 extends TBase {

	@Test
	public void loginTest() {
		lg = PageFactory.initElements(driver, login.class);
		log.info("Login Success1");
		lg.setUserName_Password(prop.getProperty("username"), prop.getProperty("password"));
		lg.clickLogin();
		TestReport.pass("passss");
		TestReport.fail("passss");
	}
	
	
	@Test
	public void loginTest2() {
		TestReport.pass("passss");
		TestReport.fail("hmmm");
	}
}
