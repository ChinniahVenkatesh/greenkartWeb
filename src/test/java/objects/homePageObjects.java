package objects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import testUtilities.testutilis;

public class homePageObjects extends testutilis{

	public homePageObjects(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public WebElement submitButton() throws IOException
	{
		return getElement(xpathgetPropertiesData("submitButton"));
	}
	

}
