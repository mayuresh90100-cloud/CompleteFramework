package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;

public class pageModel {
	
	WebDriver driver;

	public pageModel(WebDriver driver) {
    this.driver=driver;
    PageFactory.initElements(driver, this);
    
	}
	
	//Xpaths
	
	//Login section
	@FindBy(xpath="//input[@id=\"loginEmail\"]") WebElement email_placeholder;
	@FindBy(xpath="//input[@id=\"loginPassword\"]") WebElement password_placeholder;
	@FindBy(xpath="//button[contains(text(),\"Login\")]") WebElement login_button;
	
	//Create account section
	@FindBy(xpath="//input[@id=\"userName\"]") WebElement name_placeholder;
	@FindBy(xpath="//input[@id=\"userEmail\"]") WebElement emailcreateacc_placeholder;
	@FindBy(xpath="//input[@id=\"userPassword\"]") WebElement passwordcreateacc_placeholder;
	@FindBy(xpath="//input[@id=\"userAge\"]") WebElement age_placeholder;
	@FindBy(xpath="//button[contains(text(),\"Create Account\")]") WebElement createacc_button;
	
	//messages
	@FindBy(xpath="//div[contains(text(),\"User created successfully!\")]") WebElement success_message;
	@FindBy(xpath="//div[@id=\"loginSuccessMsg\"]") WebElement loginsuccess_message;
	//Action methods
	
	//Login section
	public void fillDataEmail(String email) throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(email_placeholder));
        email_placeholder.sendKeys(email);
        Thread.sleep(3000);
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: Email field not found on the page.");
		}
	}
	
	public void fillDataPassword(String password) throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(password_placeholder));
        password_placeholder.sendKeys(password);
        Thread.sleep(3000);
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: password field not found on the page.");
		}
	}
	
	public void clickLogin() throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(login_button));
        login_button.click();
        Thread.sleep(3000);
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: click button not found on the page.");
		}
	}
	
	//create section
	public void fillDataNameCreate(String name) throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(name_placeholder));
        name_placeholder.sendKeys(name);
        Thread.sleep(3000);
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: name field not found on the page.");
		}
	}
	
	public void fillDataEmailCreate(String email) throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emailcreateacc_placeholder));
        emailcreateacc_placeholder.sendKeys(email);
        Thread.sleep(3000);
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: email field not found on the page.");
		}
	}
	public void fillDataPasswordCreate(String password) throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(passwordcreateacc_placeholder));
        passwordcreateacc_placeholder.sendKeys(password);
        Thread.sleep(3000);
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: password field not found on the page.");
		}
	}
	
	public void fillDataAgeCreate(String age) throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(age_placeholder));
        age_placeholder.sendKeys(age);
        Thread.sleep(3000);
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: age field not found on the page.");
		}
	}
	public void clickCreateAcc() throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(createacc_button));
        createacc_button.click();
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: click button not found on the page.");
		}
	}
	
	public boolean checksuccessMessage() throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(success_message));
        success_message.isDisplayed();
        return true;
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: success message not found on the page.");
		}
		return false;
	}
	public boolean checkloginsuccessMessage() throws InterruptedException
	{
	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginsuccess_message));
        loginsuccess_message.isDisplayed();
        return true;
		}
		catch (NoSuchElementException e)
		{
	    System.out.println("Error: success message not found on the page.");
		}
		return false;
	}
	
}