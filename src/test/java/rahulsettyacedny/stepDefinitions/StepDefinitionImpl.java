package rahulsettyacedny.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulsettyacedeny.pageobjects.CartPage;
import rahulsettyacedeny.pageobjects.CheckoutPage;
import rahulsettyacedeny.pageobjects.ConfirmationPage;
import rahulsettyacedeny.pageobjects.LandingPage;
import rahulsettyacedeny.pageobjects.ProductCatalog;
import rahulshettyacedny.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		//code
		landingPage=lunchApplication();
	}
	
	@Given("^logged in with username (.+) and password (.+)$")
	public void loggend_in_with_username_and_Password(String username, String password)
	{
		productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProdcutToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		CartPage cartpage = productCatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckoutPage();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
		
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMassage = confirmationPage.getConfirmationMassge();
		Assert.assertTrue(confirmMassage.equalsIgnoreCase(string));
		driver.quit();

	}
	
	@Then ("{string} message is displayed")
	public void error_message_is_displayed(String stringArg1)
	{
		Assert.assertEquals(stringArg1, landingPage.getErrorMessage());
		driver.quit();
	}
}
