package TestScript;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import objects.homePageObjects;
import testBase.testBasic;

public class RegisterHomepage extends testBasic{

	public WebDriver driver;

	@Test()
	public void test01() throws IOException
	{
		driver = initBrowser();
		homePageObjects homepage = new homePageObjects(driver);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView()", homepage.submitButton());
		homepage.submitButton().click();
	}
}
