package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import base.actionMethodAPI;
import Utils.JSONUtils;
import Utils.DBUtils;
import Utils.EXLSUtils;
import base.actionMethodAPI;
import base.pageModel;
import io.restassured.response.Response;

public class DBTestCases {
	
	private DBUtils db;
	SoftAssert sa=new SoftAssert();
	String fileDBpathExcel="C:\\Users\\Prakash\\Desktop\\my workspace\\CompleteFramework\\src\\test\\resources\\DB_dataSheet.xlsx";
	
	@BeforeTest
	void DBSetUp() throws SQLException
	{
	        // Initialize DB connection
	        db = new DBUtils("jdbc:mysql://localhost:3306/pytest_db", "root", "Gayathri@12345"); 
	}

	@Test(priority=1, dataProvider="LoginData")
    public void testUserStatusInDB(String queryForemail,String queryForPassword,String DBemail,String DBpassword) {
        // Example: Validate user status after login
        
		String email = db.getCellValue(queryForemail);
		String password=db.getCellValue(queryForPassword);
        sa.assertEquals(email, DBemail);
        sa.assertEquals(password,DBpassword);
        sa.assertAll();
    }
	 @DataProvider(name = "LoginData")
	    public Object[][] LoginData() throws IOException {
	        List<List<String>> data = EXLSUtils.getAllData(fileDBpathExcel, "DB");
	        Object[][] result = new Object[data.size()][4];

	        for (int i = 0; i < data.size(); i++) {
	            result[i][0] = data.get(i).get(0); // emailQuery
	            result[i][1] = data.get(i).get(1); // passwordQuery
	            result[i][2] = data.get(i).get(2);//loginemail
	            result[i][3] = data.get(i).get(3);//loginpassword
	        }

	        return result;
	    }
}
