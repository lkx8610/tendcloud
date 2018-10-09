package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import Utility.ReadExcelPOI;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class GameAnalytics_ie {
	WebDriver driver;
	String url = "https://debug.talkingdata.com/";

	@Test(dataProvider = "dp",enabled=true)
	public void login(String email, String password, String n)
			throws InterruptedException {
		driver.get(url);

		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(3000);
		System.out.println("n+" + n);
		driver.findElement(By.id("btn-normal-login")).click();
		Thread.sleep(3000);
		// driver.findElement(By.cssSelector("input"));
		driver.findElement(
				By.xpath("html/body/div[3]/div[1]/div[2]/div/a/div[1]"))
				.click();
		Thread.sleep(6000);
		driver.findElement(
				By.xpath(".//*[@id='deeplinkclass']/div[3]/span/a[1]")).click();

		// driver.findElement(By.className("icon-logOut")).click();
		// driver.findElement(By.linkText("退出")).click();
		Thread.sleep(3000);
	}

	@BeforeMethod
	public void beforeMethod() {
		// FirefoxProfile profile = new FirefoxProfile();
		// profile.setPreference("dom.ipc.plugins.enabled", false);
		// driver = new FirefoxDriver(profile);
		// driver.manage().window().maximize();
		//	// System.setProperty("webdriver.firefox.bin","d:\\work\\firefox.exe");
		System.setProperty("webdriver.chrome.driver",
				"d:\\work\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// System.setProperty("webdriver.ie.driver","d:\\work\\IEDriverServer.exe");
		// driver=new InternetExplorerDriver();

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@DataProvider
	public Object[][] dp() throws IOException {
		return ReadExcelPOI.getTestData("d:\\testdata\\", "LoginTestData.xls",
				"Sheet2");
	}

}
