package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import base.actionMethodAPI;
import Utils.JSONUtils;
import Utils.EXLSUtils;
import base.actionMethodAPI;
import base.pageModel;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UITestCases {
	
	WebDriver driver;
	private static final Logger logger = LogManager.getLogger(UITestCases.class);
	String fileUIpathExcel="C:\\Users\\Prakash\\Desktop\\my workspace\\CompleteFramework\\src\\test\\resources\\UI_dataSheet.xlsx";
	String filepathAPICreate="C:\\Users\\Prakash\\Desktop\\my workspace\\CompleteFramework\\src\\test\\resources\\APIcreate.json";
	String filepathAPILogin="C:\\Users\\Prakash\\Desktop\\my workspace\\CompleteFramework\\src\\test\\resources\\APIlogin.json";
	
	@BeforeTest
	void launchApp()
	{
		logger.info("Launching application in Chrome...");
		driver=new ChromeDriver();
		driver.get("http://localhost:8000/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		logger.info("Application launched successfully.");
	}
	
	@Test(priority=1, dataProvider = "CreateData")
	void fillDataUI(String name,String email,String password,String age) throws InterruptedException
	{
		logger.info("Starting account creation test for user: {}", email);
		pageModel pm=new pageModel(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		pm.fillDataNameCreate(name);
		pm.fillDataEmailCreate(email);
		pm.fillDataPasswordCreate(password);
		pm.fillDataAgeCreate(age);
		pm.clickCreateAcc();
		Assert.assertEquals(pm.checksuccessMessage(),true);
		logger.info("Account created successfully for user: {}", email);
	}
	@Test(priority=2, dataProvider = "LoginData")
	void LoginDataUi(String email,String password) throws InterruptedException
	{
		logger.info("Starting login test for user: {}", email);
		pageModel pm=new pageModel(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		pm.fillDataEmail(email);
		pm.fillDataPassword(password);
		pm.clickLogin();
		Assert.assertEquals(pm.checkloginsuccessMessage(),true);
		logger.info("Login successful for user: {}", email);
	}
	
	 @DataProvider(name = "CreateData")
	    public Object[][] CreateData() throws IOException {
		 logger.debug("Fetching LoginData from Excel...");
	        List<List<String>> data = EXLSUtils.getAllData(fileUIpathExcel, "creation");
	        Object[][] result = new Object[data.size()][4];

	        for (int i = 0; i < data.size(); i++) {
	            result[i][0] = data.get(i).get(0); // name
	            result[i][1] = data.get(i).get(1); // email
	            result[i][2] = data.get(i).get(2);//password
	            result[i][3] = data.get(i).get(3);//age
	        }
	        logger.debug("CreateData loaded successfully.");
	        return result;
	    }
	 @DataProvider(name = "LoginData")
	    public Object[][] LoginData() throws IOException {
		 logger.debug("Fetching CreateData from Excel...");
	        List<List<String>> data = EXLSUtils.getAllData(fileUIpathExcel, "login");
	        Object[][] result = new Object[data.size()][2];

	        for (int i = 0; i < data.size(); i++) {
	            result[i][0] = data.get(i).get(0); // email
	            result[i][1] = data.get(i).get(1); // password
	        }
	        logger.debug("CreateData loaded successfully.");
	        return result;
	    }
	
	@AfterTest
	void tearDown()
	{
		logger.info("Closing browser...");
		driver.quit();
		logger.info("Browser closed.");
	}
}
