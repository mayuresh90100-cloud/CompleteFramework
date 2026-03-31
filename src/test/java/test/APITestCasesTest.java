package test;

import org.openqa.selenium.WebDriver;

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

public class APITestCasesTest {
	WebDriver driver;
	String fileAPIpathExcel="C:\\Users\\Prakash\\Desktop\\my workspace\\CompleteFramework\\src\\test\\resources\\API_dataSheet.xlsx";
	String filepathAPICreate="C:\\Users\\Prakash\\Desktop\\my workspace\\CompleteFramework\\src\\test\\resources\\APIcreate.json";
	String filepathAPILogin="C:\\Users\\Prakash\\Desktop\\my workspace\\CompleteFramework\\src\\test\\resources\\APIlogin.json";
	
	@Test(priority=1, dataProvider = "CreateData")
	void CreateAPI(String name,String email,String password,String age) throws InterruptedException, FileNotFoundException
	{
		File f=new File(filepathAPICreate);
		JSONObject inputPostLoginPayload = Utils.JSONUtils.readJSONFileReturnJSON(f);
		inputPostLoginPayload=Utils.JSONUtils.putValuesinPostAPICreateJson(inputPostLoginPayload, name, email, password, age);
		Response postLoginResponse = actionMethodAPI.postCreatePayload(inputPostLoginPayload.toString());
		System.out.println("POST API Login Response Body:\n" + postLoginResponse.asString());
        Assert.assertEquals(postLoginResponse.getStatusCode(), 201);
	}
	@Test(priority=2, dataProvider = "LoginData")
	void LoginAPI(String email,String password) throws InterruptedException, FileNotFoundException
	{
		File f=new File(filepathAPILogin);
		JSONObject inputPostLoginPayload = Utils.JSONUtils.readJSONFileReturnJSON(f);
		inputPostLoginPayload=Utils.JSONUtils.putValuesinPostAPILoginJson(inputPostLoginPayload,email, password);
		Response postLoginResponse = actionMethodAPI.postLoginPayload(inputPostLoginPayload.toString());
		System.out.println("POST API Login Response Body:\n" + postLoginResponse.asString());
        Assert.assertEquals(postLoginResponse.getStatusCode(), 200);
	}
	
	@DataProvider(name = "CreateData")
    public Object[][] CreateData() throws IOException {
        List<List<String>> data = EXLSUtils.getAllData(fileAPIpathExcel, "creation");
        Object[][] result = new Object[data.size()][4];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i).get(0); // name
            result[i][1] = data.get(i).get(1); // email
            result[i][2] = data.get(i).get(2);//password
            result[i][3] = data.get(i).get(3);//age
        }

        return result;
    }
	@DataProvider(name = "LoginData")
    public Object[][] LoginData() throws IOException {
        List<List<String>> data = EXLSUtils.getAllData(fileAPIpathExcel, "login");
        Object[][] result = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i).get(0); // email
            result[i][1] = data.get(i).get(1); // password
        }

        return result;
    }
}