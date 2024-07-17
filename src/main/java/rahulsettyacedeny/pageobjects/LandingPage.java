package rahulsettyacedeny.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulsettyacedeny.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); /* how this annotation knows about driver - there is initElements method
												which will have to write first to take care of constructing that
											    using driver element what you send in methods*/
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// page Factory

	@FindBy(id = "userEmail")
	private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement userpassword;
	
	@FindBy(id = "login")
	private WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	private WebElement errorMessage;
	// Page object should not hold any data, it should only focus on Elements and Actions
	
	public ProductCatalog loginApplication(String email, String password) { // action methods
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
