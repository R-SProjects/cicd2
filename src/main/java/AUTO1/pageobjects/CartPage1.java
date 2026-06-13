package AUTO1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AUTO1.Abstractcomponents.AbstractComponent1;

public class CartPage1 extends AbstractComponent1 {
	WebDriver driver;

	public CartPage1(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> selectedItems;
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement btn_checkout;

	public boolean verifyProductOnCartPage(String prod) {
		boolean match = selectedItems.stream().anyMatch(selectedItem -> selectedItem.getText().equalsIgnoreCase(prod));
		System.out.println(selectedItems.get(0).getText());
		
		return match;

	}

	public CheckOutPage1 goToCheckout(){
		btn_checkout.click();
		CheckOutPage1 checkoutpage1=new CheckOutPage1(driver);
		return checkoutpage1;
	}
}
