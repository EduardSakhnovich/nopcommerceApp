package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver rDriver)
	{
		super(rDriver);
	}
	@FindBy(id = "Email")
	WebElement txtUsername;
	@FindBy(id = "Password")
	WebElement txtPassword;
	@FindBy(css = "button.login-button")
	WebElement submitButton;
	public void setUsername(String name)
	{
		this.txtUsername.clear();
		this.txtUsername.sendKeys(name);
	}
	public void setPassword(String password)
	{
		this.txtPassword.clear();
		this.txtPassword.sendKeys(password);
	}
	public void clickSubmit()
	{
		this.submitButton.click();
	}
}