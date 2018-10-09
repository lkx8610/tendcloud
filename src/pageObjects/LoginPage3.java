package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage3 {
	WebDriver driver;
    @FindBy(how=How.NAME, using="username")WebElement username;
    @FindBy(how=How.NAME, using="password")WebElement password;
    @FindBy(how=How.NAME, using="submit")WebElement loginbutton;
	public LoginPage3(WebDriver dr) {
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	public void openPage(String url){
		driver.get(url);
	}
	public void login(String un,String pw) throws InterruptedException{
		username.sendKeys(un);
		password.sendKeys(pw);
		loginbutton.click();
		Thread.sleep(3000);
	}
	public HomePage3 login1(String un,String pw) throws InterruptedException{
		username.sendKeys(un);
		password.sendKeys(pw);
		loginbutton.click();
		Thread.sleep(3000);
		return new HomePage3(driver);
	}

}
