package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductResults {
    WebDriver driver;
    @FindBy(how=How.TAG_NAME, using="b")WebElement result;
	public ProductResults(WebDriver dr) {
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
    public void verify(String re){
    	Assert.assertEquals(re,result.getText());
    }
}
