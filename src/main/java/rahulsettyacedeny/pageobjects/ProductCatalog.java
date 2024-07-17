package rahulsettyacedeny.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulsettyacedeny.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); /*
												 * how this annotation knows about driver - there is initElements method
												 * which will have to write first to take care of constructing that
												 * using driver element what you send in methods
												 */
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// page Factory

	@FindBy(css = ".mb-3")
	private List<WebElement> products;// Page object should not hold any data, it should only focus on Elements and
	@FindBy(css =".ng-animating")
	private WebElement spinner;
	

	private By productsBy = By.cssSelector(".mb-3");
	private By addToCart = By.cssSelector(".card-body button:last-of-type");
	private By toastMessage = By.cssSelector("#toast-container");
	// Actions
	public List<WebElement> getProductList()

	{
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProdcutToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}
}
