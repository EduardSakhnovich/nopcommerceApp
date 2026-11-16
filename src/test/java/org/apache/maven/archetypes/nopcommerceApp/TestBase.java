package org.apache.maven.archetypes.nopcommerceApp;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.time.Duration;

import utilities.ReadConfig;
public class TestBase {
	
	public static WebDriver driver;
	public String baseUrl;
	public String username;
	public String password;
	public ReadConfig readConfig;

	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser)
	{
		readConfig = new ReadConfig();
		baseUrl = readConfig.getApplicationURL();
		username = readConfig.getUsername();
		password = readConfig.getPassword();
		
		logger= Logger.getLogger("nopcommerceApp");
		PropertyConfigurator.configure("log4j.properties");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			
			driver = new ChromeDriver();
		}else {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(baseUrl);
		
		System.out.println("Opened URL: " + driver.getCurrentUrl());
		System.out.println("Page title: " + driver.getTitle());
	}
	
	
	@AfterClass
	public void tearDown() throws InterruptedException
	{
	
		if(driver != null)
		{
			driver.quit();
		}
	
	}
	
	public void captureScreen(WebDriver driver, String testName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + testName + ".png");
	    FileHandler.copy(source, target);
	    System.out.println("Screenshot taken");
	}
}
