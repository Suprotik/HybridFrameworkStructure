package com.demoHFramework.testCase;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.demoHFramework.Base.TBase;
import com.demoHFramework.pageObjects.login;


public class TC_Login_002 extends TBase {

	@Test
	public void loginTest111() throws IOException {
		lg = PageFactory.initElements(driver, login.class);
		log.info("Login Success from 2");
		lg.setUserName_Password(prop.getProperty("username"), prop.getProperty("password"));
		lg.clickLogin();
		TestReport.pass("okk");
		TestReport.fail("fail").addScreenCaptureFromPath(System.getProperty("user.dir")+"//Screenshots");
	}
}
