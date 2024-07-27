package rahulsettyacedeny;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsettyacedeny.pageobjects.CartPage;
import rahulsettyacedeny.pageobjects.CheckoutPage;
import rahulsettyacedeny.pageobjects.ConfirmationPage;
import rahulsettyacedeny.pageobjects.LandingPage;
import rahulsettyacedeny.pageobjects.ProductCatalog;
import rahulshettyacedny.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException{
	 	String productName = "ZARA COAT 3";
		landingPage.loginApplication("pranitamadhup90@gmail.com", "Pranitamadhu90");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("pranitamadhup90@gmail.com", "Pranitamadhup@90");

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProdcutToCart(productName);
		CartPage cartpage = productCatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 333");
		Assert.assertFalse(match);
		System.out.println("Gitdemo code changes1");
		System.out.println("Gitdemo code changes2");
		
		System.out.println("Gitx code changes3");
		System.out.println("Gitx code changes4");
	}

}
