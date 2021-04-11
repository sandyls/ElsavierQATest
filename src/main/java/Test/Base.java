package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Base {

	public static WebDriver driver;
    public static Properties prop = new Properties();
    
    public static WebDriver initilizeDriver() throws IOException 
    {	
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties"); //Passing path dynamically   
	prop.load(fis);
	String browserName = prop.getProperty("browser");//to run browser from property file
	System.out.println(browserName);
	
	try
	{
	if(browserName.equals("chrome"))
	{
	   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\main\\java\\resources\\chromedriver.exe");		
       driver = new ChromeDriver();
       DesiredCapabilities capabilities = DesiredCapabilities.chrome();
   	   capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
   	   ChromeOptions opt= new ChromeOptions();
   	   opt.merge(capabilities);
    }
	else if(browserName.equals("firefox"))
	{
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
		driver = new FirefoxDriver();
	}	
	else if(browserName.equals("ie"))
	{
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}	
	else if(browserName.equals("remote"))
	{
		System.out.println("grid started...");			
    	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    	capabilities.setCapability("platform", "Windows XP");
    	capabilities.setCapability("version", "83");			
		driver = new RemoteWebDriver(
				new URL("http://sanda:a3b49cb8-88b4-4ad4-aa53-e65392f34e4b@ondemand.saucelabs.com:80/wd/hub"),
				capabilities);
	}
    }
	catch(Exception e)
    {
        throw new RuntimeException("Browser "+browserName+ " did not load..");
    }
		
	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	return driver;
    }
	
    public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
    {
    	TakesScreenshot ts = (TakesScreenshot) driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
    	FileUtils.copyFile(source,new File(destination));
        return destination;//for adding ss in extent reports
    }
    
	public static void QuitBrowser() 
	{
		driver.quit();
        driver = null;
	}
}
