package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage3 {
	WebDriver driver;
	@FindBy(how=How.ID,using="keyword")WebElement input;
	@FindBy(how=How.NAME,using="imageField")WebElement search;
	@FindBy(how=How.LINK_TEXT,using="退出")WebElement logout;
	public HomePage3(WebDriver dr) {
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	//实现搜索
	public void doSearch(String i) throws InterruptedException{
		input.sendKeys(i);
		search.click();
		Thread.sleep(2000);
	}
	public ProductResults doSearch1(String i) throws InterruptedException{
		input.sendKeys(i);
		search.click();
		Thread.sleep(2000);
		return new ProductResults(driver);
	}
	//实现退出操作
	public void logout(){
		logout.click();
	}
}
