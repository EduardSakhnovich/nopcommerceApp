package org.apache.maven.archetypes.nopcommerceApp;

import org.testng.annotations.Test;
import pageObjects.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DDTestLogin extends TestBase{
	
	public LoginPage loginPage;

  @Test(dataProvider="dp")
  public void ddt_test_login(String data) {
	  
	  //get data from json file
	  String  users[] = data.split(",");
	  String username = users[0];
	  String password = users[1];
	  String exp = users[2];
	  
	  loginPage.setUsername(username);
	  logger.info("Entered username");
	  loginPage.setPassword(password);
	  logger.info("Entered password");
	  loginPage.clickSubmit();
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	  wait.until(ExpectedConditions.or(
	      ExpectedConditions.urlMatches(
	          "(?i)^https?://localhost:8080/admin/?(?:[?#].*)?$"
	      ),
	      ExpectedConditions.visibilityOfElementLocated(
	          By.cssSelector(".message-error.validation-summary-errors")
	      )
	  ));
	  
	  boolean loginSuccessful = driver.getCurrentUrl().matches(
			    "(?i)^https?://localhost:8080/admin/?(?:[?#].*)?$"
			);

	  boolean expectedSuccess = exp.equals("Pass");
	  
	  Assert.assertEquals(
			    loginSuccessful,
			    expectedSuccess,
			    "Login result does not match expected result"
			);
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  logger.info("Prepare Data Driven Login test");
	  driver.manage().deleteAllCookies();
	  driver.get("http://localhost:8080/admin");
	  System.out.println("Current URL: " + driver.getCurrentUrl());
	  loginPage = new LoginPage(driver);
  }
  
  @AfterMethod
  public void afterMethod()
  {
	  logger.info("Data Driven Login  test Completed");
  }

  @DataProvider(name="dp")
  public String[] readJson()  throws IOException, ParseException
  {
	  JSONParser jsonParser = new JSONParser();
	  FileReader reader = new FileReader(".\\testdata\\testdata.json");
	  
	  Object obj = jsonParser.parse(reader);
	  JSONObject userLoginsJsonobj = (JSONObject)obj;
	  JSONArray userLoginsArray = (JSONArray)userLoginsJsonobj.get("userlogins");
	  
	  //Store JSON array data 
	  // into java array
	  
	  String arr[] = new String[userLoginsArray.size()];
	  for(int i=0; i < userLoginsArray.size(); i++ )
	  {
		  // Get index of any object
		  JSONObject users = (JSONObject)userLoginsArray.get(i);
		  String user = (String) users.get("username");
		  String pwd =  (String) users.get("password");
		  String exp =  (String) users.get("exp");
		  // fill data to array
		  arr[i] = user + "," + pwd + "," + exp;
	  }
	  return arr;
  }

}
