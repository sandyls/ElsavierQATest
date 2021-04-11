package Test.pages;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	public WebDriver driver;	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//ul[@id='homefeatured']")
	private WebElement productList;
	
    @FindBy(xpath = "//ul[@id='homefeatured']/li[5]/div/div[1]/div/a[1]/img")
    private WebElement hoverOnElement;
    
    @FindBy(xpath = "//a[@title='Printed Summer Dress']")
    private WebElement summerDress;
    
    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    private WebElement addToCart;
    
//////////////////////////////////////////////////////////////////////////////////////    
	public void verifyPageTitle() throws InterruptedException
	{
	   Assert.assertTrue(driver.getTitle().contains("My Store"));
	   Thread.sleep(2000);
	}
	
	public void addSummerDressesToCart() throws InterruptedException
	{	
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-1500)");
		List<WebElement> products = driver.findElements(By.xpath("//ul[@id='homefeatured']"));
		System.out.println(products.size());
		for(int i=0; i<products.size();i++)
		{	
			String prd = "Printed Summer Dress";
			String pname = products.get(i).getText();
			if(pname.contains(prd))
			{
			driver.findElements(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[5]")).get(i).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
			driver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[4]/div[1]/div[1]/span[1]")).click();
		    }
		}	

		
		
	}

}
