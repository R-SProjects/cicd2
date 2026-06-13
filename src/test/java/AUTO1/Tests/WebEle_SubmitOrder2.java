package AUTO1.Tests;

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

import AUTO1.pageobjects.LandingPage;
import AUTO1.pageobjects.ProductCat;

public class WebEle_SubmitOrder2 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		LandingPage landingpage=new LandingPage(driver);
		landingpage.goToUrl();
		landingpage.loginApplication("abc@gmail.com","Roopa@123");
		
		String productToAdd = "ZARA COAT 3";
		ProductCat prodcat =new ProductCat(driver);
		//prodcat.getProdName(productToAdd);
		prodcat.addToCart(productToAdd);
		

		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection h3")));
		List<WebElement> selectedItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = selectedItems.stream()
				.anyMatch(selectedItem -> selectedItem.getText().equalsIgnoreCase(productToAdd));
		System.out.println(selectedItems.get(0).getText());
		Assert.assertTrue(match);
		System.out.println(match);
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		String country = "India";
		String c = (String.valueOf(country.charAt(3)));
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(country.split(c)[0]);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("section.ta-results button")));
		List<WebElement> countryList = driver.findElements(By.cssSelector("section.ta-results button"));
		for (WebElement coun : countryList) {
			if (coun.getText().equalsIgnoreCase(country)) {
				System.out.println(coun.getText());
				coun.click();
				break;

			}
			System.out.println("dd" + coun.getText());
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.className("action__submit")).click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".hero-primary")));
		driver.findElement(By.className("hero-primary")).getText();
		String orderConfmessage = "Thankyou for the order.";
		Assert.assertEquals(driver.findElement(By.className("hero-primary")).getText(), orderConfmessage.toUpperCase());
		System.out.println("Successfull from framework structure ");
	}

}
