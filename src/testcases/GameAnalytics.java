package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.ReadExcelPOI;
import Utility.utills;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

public class GameAnalytics {
	WebDriver driver;
	String url = "https://www.talkingdata.com/";
//bxu_cn@163.com 123456
	@Test(dataProvider = "dp",priority=0)
	public void login(String email, String password,String n) throws InterruptedException {
		driver.get(url);
		
		
		driver.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div[1]/div/div[1]/a/div/hr")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(3000);
		System.out.println("n+"+n);
		driver.findElement(By.id("btn-normal-login")).click();
		Thread.sleep(3000);
		//driver.findElement(By.cssSelector("input"));
//		driver.findElement(By.xpath("html/body/div[3]/div[1]/div[2]/div/a/div[1]")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(".//*[@id='deeplinkclass']/div[3]/span/a[1]")).click();
		
		//driver.findElement(By.className("icon-logOut")).click();
		// driver.findElement(By.linkText("退出")).click();
		//Thread.sleep(3000);
		//Thread.sleep(6000);
	
	}
	
	
	@Test(priority = 1)
	public void gameReport() throws InterruptedException {
		 JavascriptExecutor jse=(JavascriptExecutor) driver;
		//移动到窗口绝对位置坐标，如下移动到纵坐标300像素位置 ，Selenium之Web页面滚动条滚操作。
		 jse.executeScript("window.scrollBy(0, 300)");  
		Thread.sleep(3000);
		//移动广告监测
	//	driver.findElement(By.xpath("html/body/div[3]/div[1]/div[2]/div/a/div[1]")).click();
		//game                       html/body/div[3]/div[1]/div[4]/div[2]/a/div[1]
		driver.findElement(By.xpath("html/body/div[3]/div[1]/div[4]/div/a/div[1]")).click();
		Thread.sleep(3000);
		//app
	//	driver.findElement(By.xpath("html/body/div[3]/div[1]/div[3]/div/a/div[2]/div[1]")).click();
     //Game监控测试
		driver.findElement(By.xpath(".//*[@id='list']/div[3]/table/tbody/tr[7]/td[1]/a")).click();

      Thread.sleep(8000);
      //日报
      driver.findElement(By.xpath(".//*[@id='menu-banner']/div/ul/li[1]/ol/li[2]/a")).click();
      Thread.sleep(5000);
      //平均在线
      String rate=driver.findElement(By.xpath(".//*[@id='dailyReport-tbody']/tr[2]/td[2]")).getText();
     
      new SoftAssert().assertEquals(rate, "2");
     // Assert.assertEquals(rate, "190");
      System.out.println("ok");
      
      
	driver.findElement(By.xpath(".//*[@id='menu-banner']/div/ul/li[2]/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='menu-banner']/div/ul/li[2]/ol/li[1]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='newPlayerCode']/div[2]/div[3]/a[2]/font")).click();
		Thread.sleep(8000);
//		driver.findElement(By.className("login")).click();
//		driver.findElement(By.id("email")).sendKeys(email);
//		driver.findElement(By.id("password")).sendKeys(password);
//		Thread.sleep(3000);
//		System.out.println("n+"+n);
//		driver.findElement(By.id("btn-normal-login")).click();
//		Thread.sleep(3000);
//		//driver.findElement(By.cssSelector("input"));
//		
//		
//		
//		//driver.findElement(By.className("icon-logOut")).click();
//		// driver.findElement(By.linkText("退出")).click();
		
	}
	  
	@BeforeSuite
	private void BeforeSuite() {
		
//		FirefoxProfile profile = new FirefoxProfile();
//		profile.setPreference("dom.ipc.plugins.enabled", false);
//		driver = new FirefoxDriver(profile);
		// System.setProperty("webdriver.firefox.bin","d:\\work\\firefox.exe");
		 System.setProperty("webdriver.chrome.driver","d:\\work\\chromedriver.exe");
		 driver=new ChromeDriver();
		driver.manage().window().maximize();

		// System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		// driver=new ChromeDriver();

		// System.setProperty("webdriver.ie.driver","d:\\work\\IEDriverServer.exe");
		// driver=new InternetExplorerDriver();
	}
	@AfterSuite
	private void AfterSuite() {

		driver.quit();
	}
	@BeforeMethod
	public void beforeMethod() {

		

	}

	@AfterMethod
	public void afterMethod() {
	
	}

	@DataProvider
	public Object[][] dp() throws IOException {
		return ReadExcelPOI.getTestData("d:\\testdata\\", "LoginTestData.xls",
				"Sheet2");
	}

}
