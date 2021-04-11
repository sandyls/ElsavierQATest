package Test.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage {

	public WebDriver driver;	
	public AuthenticationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[contains(text(),'Authentication')]")
	private WebElement authenticationElement;
	
	@FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[1]/p[2]/button[1]/span[1]")
	private WebElement signInelement;
	
	public void onAuthenticationPage() throws InterruptedException
	{
	   Assert.assertTrue(authenticationElement.isDisplayed());
	}
	
	public void signInSession() throws InterruptedException
	{
	   Assert.assertTrue(signInelement.isDisplayed());
	}
}
