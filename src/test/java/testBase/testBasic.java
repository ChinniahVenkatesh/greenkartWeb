package testBase;

import java.awt.Desktop;
import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.DesktopManager;
import javax.swing.text.html.CSS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class testBasic {

	public static WebDriver driver;
	public static Properties prop;
	static String path1 = System.getProperty("user.dir")+"\\src\\test\\java\\locators\\xpath.properties";
	static String path = System.getProperty("user.dir")+"\\src\\main\\java\\TestConfiguration\\testdataconfig.properties";
	public static org.apache.logging.log4j.Logger logger;
	public static ExtentReports reports;
	public static ExtentSparkReporter reporter;
	public static ExtentTest test;
	public static WebDriver  initBrowser() throws IOException
	{
		
		String browser = testBasic.getPropertiesData("browser");
		System.out.println(browser);
		String pageUrl = testBasic.getPropertiesData("domain_url");
		String headless =  testBasic.getPropertiesData("headlessmode");
		boolean headlessMode = headless.contains("true");
		System.out.println(headlessMode);
		switch(browser)
		{
		case "edge": 
				if(headlessMode)
				{
					EdgeOptions options = new EdgeOptions();
					options.addArguments("--headless");
					driver = new EdgeDriver(options);
				}
				else
				{
					driver = new EdgeDriver();
				}
				driver.manage().window().maximize();
				
			break;
		case "chrome":
				
				if(headlessMode)
				{
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
					driver = new ChromeDriver(options);
				}
				else
				{
					driver = new ChromeDriver();
				}
				
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("No browser is configured here for the "+browser);
		}
		driver.get(pageUrl);
		test();
		Logger4j().info("The browser is "+browser+" is invoked");
		
		return driver;
	}
	
	public static  String getPropertiesData(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream(path);
		prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
	public static  String xpathgetPropertiesData(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream(path1);
		prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
	public static  Logger Logger4j()
	{
		logger = LogManager.getLogger();
		return logger;
	}
	
	
	public static ExtentReports ExtentTestReports()
	{
		reporter = new ExtentSparkReporter("index.html");
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reporter.config().setTheme(Theme.DARK);
		return reports;
	}
	
	public static ExtentTest test()
	{
		test = ExtentTestReports().createTest("Test01");
		return test;
	}
	
	public static ExtentTest testPass(String txt)
	{
		test.log(Status.PASS, txt, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot()).build());
		//test.log(Status.PASS,MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot()).build());
		return test;
	}
	
	public static void finish() throws IOException, URISyntaxException
	{
		driver.quit();
		reports.flush();
		Desktop d = Desktop.getDesktop();
		String path = System.getProperty("user.dir")+"\\index.html";
		File file = new File(path);
		//d.browse(new URI(path));
		d.open(file);
		
	}
	
	public static  String getScreenshot()
	{
	  String file =  ((RemoteWebDriver) driver).getScreenshotAs(OutputType.BASE64);
	  return file;
	}
	
	public static void validation(String actual, String expected)
	{
			if(actual.contains(expected))
			{
				String[][] addString = {{"Validation","Actual","Expected"},{"",actual,expected}};
				test.log(Status.PASS,MarkupHelper.createTable(addString));
			}
	}
	
}
