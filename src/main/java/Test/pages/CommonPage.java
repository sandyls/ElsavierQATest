package Test.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonPage {

	public WebDriver driver;	
	public CommonPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//header/div[3]/div[1]/div[1]/div[3]/div[1]/a[1]")
	private WebElement goToCartPage;
	
	public void goToCart() throws InterruptedException
	{
		goToCartPage.click();
	}
}
