package AUTO1.Abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AUTO1.pageobjects.CartPage1;

public class AbstractComponent1 {
	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponent1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement btn_cart;
	@FindBy(css = "button[routerlink*='myorders'")
	WebElement btn_Orders;

	public void waitForElementToDisappearWebElement(WebElement webelement) {
		wait.until(ExpectedConditions.invisibilityOf(webelement));
	}

	public void waitForElementToDisappearByLoc(By byloc) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byloc));
	}

	public void waitForElementToAppearByLoc(By byloc1) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(byloc1));
	}

	public void waitForElementsToAppearByLoc(By byloc2) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byloc2));
	}

	public void waitForElementToBeClicableByLoc(By byloc3) {
		wait.until(ExpectedConditions.elementToBeClickable(byloc3));
	}

	
	public CartPage1 goToCart() {
		btn_cart.click();
		CartPage1 cartpage1 = new CartPage1(driver);
		return cartpage1;

	}
	public OrdersPage1 goToOrders() {
		btn_Orders.click();
		OrdersPage1 orderspage1 = new OrdersPage1(driver);
		return orderspage1;

	}
}
