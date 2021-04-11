package Test.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	public WebDriver driver;	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[@id='cart_title']")
	private WebElement cartTitle;
	
	@FindBy(xpath="//tbody/tr[@id='product_5_19_0_0']/td[2]/p[1]/a[1]")
	private WebElement itemInCart ;
	
	@FindBy(xpath="//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/p[2]/a[1]/span[1]")
	private WebElement proceedToCheckoutButton ;
	
	
	public void onCartPage() throws InterruptedException
	{
	   Assert.assertTrue(cartTitle.isDisplayed());
	}
	
	public void itemOnCartPage() throws InterruptedException
	{
	   Assert.assertTrue(itemInCart.isDisplayed());
	   Thread.sleep(2000);
	}
	
	public void goToCheckoutPage() throws InterruptedException
	{
	   JavascriptExecutor jse = (JavascriptExecutor)driver;
	   jse.executeScript("window.scrollBy(0,-1700)");
	   Assert.assertTrue(proceedToCheckoutButton.isEnabled());
	   Thread.sleep(2000);
	   WebDriverWait wait = new WebDriverWait(driver,5);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/p[2]/a[1]/span[1]")));
	   proceedToCheckoutButton.click();
	}
}
