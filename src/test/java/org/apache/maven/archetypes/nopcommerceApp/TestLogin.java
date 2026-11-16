package org.apache.maven.archetypes.nopcommerceApp;

import org.testng.annotations.Test;

import pageObjects.LoginPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.testng.Assert;



public class TestLogin extends TestBase{
	
	public LoginPage loginPage;
	
  @Test
  public void test_login() throws IOException{
	  

	  
	  String actualTitle = driver.getTitle();
	  String expectedTitle = "Dashboard / nopCommerce administration";
	  
	  logger.info("Login validation started......");
	  
	  if(!actualTitle.equals(expectedTitle))
	  {
		  captureScreen(driver, "test_login");
		  logger.warn("Login test failed");
	  }
	  
	  Assert.assertEquals(
			  actualTitle,
			  expectedTitle,
			  "Unexpected page title after login"
			  );
	  
	  logger.info("Login test passed");
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  
	  logger.info("Prepare Login test");
	  
	   System.out.println("CURRENT URL: " + driver.getCurrentUrl());
	   System.out.println("PAGE TITLE: " + driver.getTitle());
	   if (driver.getTitle().contains("Just a moment")) {
		    throw new org.testng.SkipException(
		        "Login test skipped: Cloudflare challenge blocks login page"
		    );
		}
	  
	  loginPage = new LoginPage(driver);
	  loginPage.setUsername(username);
	  logger.info("Entered username");
	  loginPage.setPassword(password);
	  logger.info("Entered password");
	  loginPage.clickSubmit();
	   
  }
  
  @AfterMethod
  public void afterMethod()
  {
	  logger.info("Login test Completed");
  }


}
