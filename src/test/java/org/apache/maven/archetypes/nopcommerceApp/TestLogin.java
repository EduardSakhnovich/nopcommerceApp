package org.apache.maven.archetypes.nopcommerceApp;

import org.testng.annotations.Test;

import pageObjects.LoginPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class TestLogin extends TestBase{
	
	public LoginPage loginPage;
	
  @Test
  public void test_login() throws IOException{
	  
	  logger.info("Validating login");
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	  
	  boolean loginSuccessful = wait.until(
			    d -> d.getCurrentUrl().matches(
			        "(?i)^https?://localhost:8080/admin/?(?:[?#].*)?$"
			    )
			);
	  
	  if(!loginSuccessful)
	  {
		  captureScreen(driver, "test_login");
	  }
	  
	  Assert.assertTrue(loginSuccessful, "Login failed: admin dashboard was not opened");
	  
	  logger.info("Login test passed");
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  
	  logger.info("Prepare Login test");
	  
	  loginPage = new LoginPage(driver);
	  loginPage.setUsername(username);
	  loginPage.setPassword(password);
	  loginPage.clickSubmit();
	  logger.info("Login submitted");
	   
  }
  
  @AfterMethod
  public void afterMethod()
  {
	  logger.info("Login test Completed");
  }


}
