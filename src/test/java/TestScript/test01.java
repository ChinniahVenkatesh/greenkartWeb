package TestScript;

import java.io.IOException;


import org.openqa.selenium.WebDriver;

import testUtilities.testutilis;

public class test01 extends testutilis{
	
	
	public test01(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	static WebDriver driver;
	public static void main(String [] args) throws IOException
	{
		driver = initBrowser();
		testutilis testutilis  = new testutilis(driver);
		testutilis.getElement("//input[@placeholder='Search for Vegetables and Fruits']").sendKeys("Tomato");
		testPass("going to close the browser");
		finish();
		
		
		
		
		
	}
	
}
