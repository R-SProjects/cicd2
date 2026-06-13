package AUTO1.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AUTO1.Abstractcomponents.AbstractComponent1;

public class LandingPage extends AbstractComponent1{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// to get the driver from the calling class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userid;
	@FindBy(id = "userPassword")
	WebElement userpass;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement btn_login;
	@FindBy (css=".toast-error")
	WebElement toastmsg;
	// //div[@aria-label='Incorrect email or password.']
	
	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public ProductCat loginApplication(String user,String passw) {
		userid.sendKeys(user);
		userpass.sendKeys(passw);
		btn_login.click();
		ProductCat prodcat =new ProductCat(driver);
		return prodcat;
	}
	public String getErrorMessage() {
		return toastmsg.getText();
	}
}
