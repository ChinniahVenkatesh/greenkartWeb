package testUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import testBase.testBasic;

public class testutilis extends testBasic{
	
	static WebDriver driver;
	public testutilis(WebDriver driver)
	{
		this.driver = driver;
	}
	public static WebElement getElement(String element)
	{
		Logger4j().info("The Element is "+element);
		testPass(element);
		return driver.findElement(By.xpath(element));
			
	}

}
