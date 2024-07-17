package rahulsettyacedeny;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsettyacedeny.pageobjects.CartPage;
import rahulsettyacedeny.pageobjects.CheckoutPage;
import rahulsettyacedeny.pageobjects.ConfirmationPage;
import rahulsettyacedeny.pageobjects.LandingPage;
import rahulsettyacedeny.pageobjects.OrderPage;
import rahulsettyacedeny.pageobjects.ProductCatalog;
import rahulshettyacedny.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProdcutToCart(input.get("ProductName"));
		CartPage cartpage = productCatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckoutPage();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMassage = confirmationPage.getConfirmationMassge();
		Assert.assertTrue(confirmMassage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHystoryTest() {
		// To verify "ZARA COAT 3" is displaying in orders Page
		ProductCatalog productCatalog = landingPage.loginApplication("pranitamadhup90@gmail.com", "Pranitamadhup@90");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		orderPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

	}

	public String getScreeshot(String testCaseName) throws IOException {
		TakesScreenshot	ts= (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" +testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" +testCaseName + ".png";
	}
	
	//Extent Reports
	// if you runs the Test with 2 different DATA sets with HashMap

	@DataProvider
	public Object[][] getData() throws IOException {
		
		// 1st curly bracket is 1st data set
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//rahulsettyacedeny//data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	
	
	/*------------------------------------------------------------------------
	 * if you runs the Test with 2 different DATA sets
	 * 
	 * @DataProvider public Object[][] getData() { //1st carly braket is 1st data
	 * set return new Object[][] { {"pranitamadhup90@gmail.com", "Pranitamadhup@90",
	 * "ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"} }; }
	 * 
	 */
	//--------------------------------------------------------------------------
	// 		HashMap<String, String> map = new HashMap<String, String> ();
			// map.put("email", "pranitamadhup90@gmail.com");
			// map.put("password", "Pranitamadhup@90");
			// map.put("ProductName", "ZARA COAT 3");

			// HashMap<String, String> map1 = new HashMap<String, String> ();
			// map1.put("email", "shetty@gmail.com");
			// map1.put("password", "Iamking@000");
			// map1.put("ProductName", "ADIDAS ORIGINAL");

}
