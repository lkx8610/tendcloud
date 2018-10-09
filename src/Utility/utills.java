package Utility;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class utills {
	public static WebDriver driver;

	/**
	 * This method implements how to switch windows
	 * 
	 * 
	 * @param cwindow
	 */
	public static void switchWindows(String cwindow) {

		Set<String> Ahandles = driver.getWindowHandles();
		Iterator<String> Ait = Ahandles.iterator();
		while (Ait.hasNext()) {
			String crWindowString = Ait.next();
			System.out.println("next = " + crWindowString);
			if (cwindow == crWindowString) {
				continue;
			}
			driver.switchTo().window(crWindowString);
		}
	}

	/**
	 * This method implemenets how to get the specified test data based on the
	 * column name(key).
	 * 
	 * @param key
	 * @return
	 */
	public static String getTestData(String key) {
		ReadExcelData.setPath(Contants.path + Contants.filename,
				Contants.sheetname);
		int rowNum = ReadExcelData.getRowContains(key, Contants.keycolumn);
		String cellValue = ReadExcelData.getCellData(rowNum, Contants.column);
		return cellValue;

	}

	public static String getCSVTestData(String key) {
		String vlaue = null;
		for (String[] row : ReadExcelData.getLocatorsFromObjectsFile()) {
			if (row[0].equalsIgnoreCase(key)) {
				vlaue = row[1];

			}
		}
		return vlaue;
	}

	/**
	 * @param key
	 * 
	 *            标题 1.Logical_Name 2.Locator_Type 3.Locator 1.
	 *            username_editbox_locator 2.xpath 3.
	 *            .//*[@id='account']/div/input 测试数据有三列
	 * @return
	 */
	public static By getLocator(String key) {
		String locatorType = null;
		String locator = null;
		By locat = null;

		for (String[] row : ReadExcelData.getLocatorsFromObjects()) {
			if (row[0].equalsIgnoreCase(key)) {
				locatorType = row[1];
				locator = row[2];
				break;
			}
		}
		switch (locatorType) {
		case "id":
			locat = By.id(locator);
			break;
		case "name":
			locat = By.name(locator);
			break;
		case "xpath":
			locat = By.xpath(locator);
			break;
		case "linkText":
			locat = By.linkText(locator);
			break;
		default:
			Log.warn("Can not find the locator type. locator type is:"
					+ locatorType);
			break;
		}
		return locat;
	}

	/**
	 * this method implements how to get an element .
	 * 
	 * @param key
	 * @return
	 */
	public static WebElement getElement(String key) {
		WebElement element;
		element = driver.findElement(getLocator(key));
		if (!element.isDisplayed()) {// 元素不可见
			Log.error("Can not find the element:" + element);
		}
		return element;
	}

	/**
	 * @param value
	 *            测试数据
	 * @param locator
	 *            定位地址
	 * @param flag
	 *            true 代表从页面获取，false 代表从文件读取
	 */
	public static void input(String value, String locator, boolean flag) {
		String inputData = "";
		if (flag) {
			inputData = value;
			if (inputData != null) {
				getElement(locator).clear();
				getElement(locator).sendKeys(inputData);
				Log.info("test data:" + inputData + " is input");
			}
		} else {

			inputData = utills.getTestData(value);
			if (inputData != null) {
				getElement(locator).clear();
				getElement(locator).sendKeys(inputData);
				Log.info("test data:" + inputData + " is input");
			}
		}
	}

	public static void openBrowser(String url, String browser) {
		switch (browser) {
		case "firefox":
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("security.mixed_content.block_ative_content",
					false);
			profile.setPreference(
					"security.mixed_content.block_display_content", true);
			driver = new FirefoxDriver(profile);
			Log.info("firefox");
			break;
		case "ie":
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);

			System.setProperty("webdriver.ie.driver", Contants.path
					+ Contants.ieDriverServer);
			driver = new InternetExplorerDriver(ieCapabilities);
			System.out.println("setCapability");
			// driver = new InternetExplorerDriver();
			
			
			break;
		case "chrome":
			driver = new FirefoxDriver();
			break;

		}

		driver.get(url);
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void elementClick(String locator) {
		getElement(locator).click();
		// waitforJsload();//切换页面和查询（检索）时需要等待

	}

	public static void switchFrame(String id) {
		driver.switchTo().frame(id);

	}

	public static void selectValue(String value, String locator, String flag) {
		Select select = new Select(getElement(locator));
		String datavalue=getTestData(value);
		if (flag.equalsIgnoreCase("byvalue")) {
			select.selectByValue(datavalue);
		} else {

			select.selectByVisibleText(datavalue);// 页面显示的值}
		}
		Log.info(datavalue+"is selected");
	}
	
	
	
}
