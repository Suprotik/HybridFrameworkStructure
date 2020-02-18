package com.demoHFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class login {
	
	@FindBy(xpath = "//input[@name='uid']") public WebElement userID;
	@FindBy(xpath = "//input[@name='password']") public WebElement password;
	@FindBy(xpath = "//input[@name='btnLogin']") public WebElement loginButton;
	
	
	WebDriver driver;
	/*login(WebDriver Ldriver){
		driver = Ldriver;
		PageFactory.initElements(Ldriver, this);
	}*/
	
	
	public void setUserName_Password(String uname,String pass) {
		userID.sendKeys(uname);
		password.sendKeys(pass);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
}
