package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@CacheLookup
	@FindBy(how=How.ID_OR_NAME,using="username")WebElement usern;
	@FindBy(name="password")WebElement passwor;
	@FindBy(name="submit")WebElement submit;
	
	public LoginPage(WebDriver dr) {
		this.driver =dr;
		PageFactory.initElements(driver, this);
	}
	public void open(String url) throws InterruptedException{
		driver.get(url);
		Thread.sleep(2000);
	}
	public void login(String un,String pw) throws InterruptedException{

		usern.sendKeys(un);
		passwor.sendKeys(pw);
		submit.click();
		Thread.sleep(3000);
	}
}
