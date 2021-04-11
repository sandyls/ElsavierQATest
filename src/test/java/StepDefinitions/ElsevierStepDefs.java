package StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Test.Base;
import Test.pages.AuthenticationPage;
import Test.pages.CartPage;
import Test.pages.CommonPage;
import Test.pages.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ElsevierStepDefs extends Base{


	public static Logger log = LogManager.getLogger(Base.class.getName());
	HomePage home;
	CartPage cart;
	CommonPage comm;
	AuthenticationPage auth;
	
	
/////////////////////////////////////////////////////////////////////////////////////////	
	@Given("^User navigate to given URL$")
	public void user_navigate_to_given_URL() throws Throwable {
		driver=initilizeDriver(); 
		log.info("Driver is initializing...");
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}

	@When("^User add 'summer dresses' to cart$")
	public void user_add_summer_dresses_to_cart() throws Throwable {
		home  = new HomePage(driver);
		home.verifyPageTitle();
		log.info("User is on home page");
	    home.addSummerDressesToCart();
	}

	@Then("^User will be able to see summer dresses in cart$")
	public void user_will_be_able_to_see_summer_dresses_in_cart() throws Throwable {
	   comm = new CommonPage(driver);
	   comm.goToCart();
	   cart = new CartPage(driver);
	   cart.onCartPage();
	   log.info("User is on cart page");
	   cart.itemOnCartPage();		
	}

	@Then("^User will be able to go to 'Check out' page$")
	public void user_will_be_able_to_go_to_Check_out_page() throws Throwable {
		cart = new CartPage(driver);
        cart.goToCheckoutPage();
	}

	@Then("^User will be able to go to 'sign in' page$")
	public void user_will_be_able_to_go_to_sign_in_page() throws Throwable {
	    auth = new AuthenticationPage(driver);
	    auth.onAuthenticationPage();
	    auth.signInSession();
	    log.info("SignIn session is available");
	    driver.quit();
	}

}
