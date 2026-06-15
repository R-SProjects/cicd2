package AUTO1.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AUTO1.TestComponents.BaseTest1;
import AUTO1.TestComponents.Retry1;
import AUTO1.pageobjects.CartPage1;
import AUTO1.pageobjects.CheckOutPage1;
import AUTO1.pageobjects.ConfirmationPage1;
import AUTO1.pageobjects.LandingPage;
import AUTO1.pageobjects.ProductCat;

public class ErrorValidationTest extends BaseTest1 {
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry1.class)
	public void LoginErrorValidation() throws IOException {
		//launchApplication();
		landingpage.loginApplication("abc@gmail.com", "Roopsa@123");
		String productToAdd = "ZARA COAT 3";
		Assert.assertEquals(landingpage.getErrorMessage(),"Incorrect email or password.");
		System.out.println("ErroHand");
		
	}

	@Test
	public void ProductErrorValidation() throws IOException {
		ProductCat prodcat = landingpage.loginApplication("efg@gmail.com", "Roopa@123");
		String productToAdd = "ZARA COAT 3";
		prodcat.addToCart(productToAdd);
		CartPage1 cartpage1 = prodcat.goToCart();
	//	//div[@class='ng-tns-c4-54 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
		boolean match = cartpage1.verifyProductOnCartPage("ZARA COAT 33");
		Assert.assertFalse(match);
		System.out.println("Successfull-Product Error Validation");
}
}