package testcases;

import java.util.HashMap;
import java.util.UUID;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class CountingTest {

	private long count = 0;
	private HashMap<Integer, String> hashMap;

	public long getCount() {
		return count;
	}
//singleThreaded=true
	@Test(invocationCount = 300, threadPoolSize = 100, dataProvider = "Test")
	public void service(Integer n, String s) {

		hashMap = new HashMap<Integer, String>();
		hashMap.put(n, s);
		++count;
		System.out.println("count value{}afterMethod:" + hashMap.get(n));
		// HashMap<String, String> hashMap2 = new HashMap<String,String>();
		// hashMap2.put("1", "1");
		// hashMap2.put("1", "1");
		// hashMap2.put("1", "1");
		
	}

	// @Test(dataProvider = "dp")
	// public void f(Integer n, String s) {
	// }
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	
		System.out.println("count value{}afterMethod:" + count);
		System.out.println("UUID:" + 	UUID.randomUUID().toString());

	}

	// parallel=true
	@DataProvider(name = "Test")
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" },
				new Object[] { 2, "b" }, new Object[] { 3, "c" } };
	}

	@BeforeClass
	public void beforeClass() {
		
	}

	@AfterClass
	public void afterClass() {
		System.out.println("count value{}afterClass:" + count);
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
		System.out.println("count value{}afterTest:" + count);
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("count value{}afterSuite:" + count);
	}

}
