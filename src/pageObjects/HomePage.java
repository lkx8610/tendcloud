package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(how=How.LINK_TEXT,using="退出")WebElement logout;
	
	public HomePage(WebDriver dr) {
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	public void logout() throws InterruptedException{
		logout.click();
		Thread.sleep(3000);
	}
}
