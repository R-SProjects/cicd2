package AUTO1.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AUTO1.Abstractcomponents.OrdersPage1;
import AUTO1.TestComponents.BaseTest1;
import AUTO1.pageobjects.CartPage1;
import AUTO1.pageobjects.CheckOutPage1;
import AUTO1.pageobjects.ConfirmationPage1;
import AUTO1.pageobjects.LandingPage;
import AUTO1.pageobjects.ProductCat;

public class SubmitOrderTest extends BaseTest1 {
	//String productToAdd = "ZARA COAT 3";
	@Test(dataProvider="getData",groups="PurchaseOrder")
	public void submitOrder(HashMap<String,String> input) throws IOException {
		ProductCat prodcat = landingpage.loginApplication(input.get("username"), input.get("passw"));
		
		// prodcat.getProdName(productToAdd);
		prodcat.addToCart(input.get("productToAdd"));
		CartPage1 cartpage1 = prodcat.goToCart();

		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection
		// h3")));
		boolean match = cartpage1.verifyProductOnCartPage(input.get("productToAdd"));
		Assert.assertTrue(match);
		CheckOutPage1 checkoutpage1 = cartpage1.goToCheckout();
		String country = "India";
		checkoutpage1.doCheckOut("India");

		// wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".hero-primary")));

		ConfirmationPage1 confirmpage = checkoutpage1.submitOrder();
		String orderConfmessageexpect = "Thankyou for the order.";
		Assert.assertEquals(confirmpage.getConfirmation(), orderConfmessageexpect.toUpperCase());
		System.out.println("Successfull from framework structure ");
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void  VerifyOrdersPage() {
		ProductCat prodcat = landingpage.loginApplication("abc@gmail.com", "Roopa@123");
		OrdersPage1 orderspage1 = prodcat.goToOrders();
		Assert.assertTrue(orderspage1.verifyProductsOrder("ZARA COAT 3"), "Assert true");
	}
//	@DataProvider
//	public Object[][]  getData() {
//		return new Object[][] {{"abc@gmail.com","Roopa@123","ZARA COAT 3"},{"efg@gmail.com", "Roopa@123","ADIDAS ORIGINAL"}};
//	}
	//Using hashmap
//	@DataProvider
//	public Object[][]  getData() {
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("username", "abc@gmail.com");
//		map1.put("passw", "Roopa@123");
//		map1.put("productToAdd","ZARA COAT 3");
//		HashMap<String,String> map2=new HashMap<String,String>();
//		map2.put("username", "efg@gmail.com");
//		map2.put("passw", "Roopa@123");
//		map2.put("productToAdd","ADIDAS ORIGINAL");		
//		return new Object[][] {{map1},{map2}};
//	}
	
	
	//Using json hashmap
		@DataProvider
		public Object[][]  getData() throws IOException {
			List<HashMap<String, String>> testdata1 = getJsonDataTohashMap(System.getProperty("user.dir") +"\\src\\test\\java\\AUTO1\\Data\\PurchaseOrder.json");
			return new Object[][] {{testdata1.get(0)},{testdata1.get(1)}};
		}

}
