package core;

import static utility.Report.test;

import static utility.Report.*;
import static utility.ScreenShot.*;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



import utility.Report.*;
import utility.Report;

public class Page 
{
	public static WebDriver driver= null;
	public Properties pr;
	
	@Parameters({"browser","key"})
	@BeforeTest
	public void beforeTest(String browser,String key) throws Exception
	{
		//loading config file properties
		FileReader fr=new FileReader("C:\\Users\\rjpta\\eclipse-workspace\\GNMultiClient\\src\\test\\resources\\configFile\\config.properties");
		pr=new Properties();
		pr.load(fr);
			
		
		if(!Boolean.parseBoolean(key))
		{
			throw new SkipException("skip test ");
		}
		else
		{
			
			//ExtentTest test =Report.getExtent().createTest("browser");
			//browser init...
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				driver=new EdgeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				driver= new FirefoxDriver();
			}
			else
			{
				System.err.println("error in input....");
			}
			//driver.get(pr.getProperty("GNUrl"));
			
			driver.get(pr.getProperty("mercyUrl"));;

			//compulsort to init driver in Pagefactory to read external xpath
			PageFactory.initElements(driver, this);

			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
			driver.manage().window().maximize();
		}

	}

	@AfterTest
	public void afterTest()
	{
		// close the browser session 
		driver.quit();

		
		//Report.getExtent().flush();
	}
}
