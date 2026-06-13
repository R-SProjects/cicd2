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

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingpage=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Roopa@123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String productToAdd = "ZARA COAT 3";
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card']//h5")));
		//List<WebElement> products = driver.findElements(By.xpath("//div[@class='card']//h5"));
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card']//h5"));
		System.out.println("Product Size " + products.size());
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getText().equalsIgnoreCase(productToAdd)) {
				products.get(i).findElement(By.xpath("//h5/b[contains (text(),'" + productToAdd
						+ "')]/parent::h5/following-sibling::button[text()=' Add To Cart']")).click();
				System.out.println("Clicked " + products.get(i).getText());
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ngx-spinner"))));
				System.out.println(driver.findElement(By.cssSelector(".toast-bottom-right")).isDisplayed());
				System.out.println(driver.findElement(By.cssSelector(".toast-bottom-right")).getText());
				wait.until(
						ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".toast-bottom-right"))));

			}
		}
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

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
		System.out.println("Successfull ");
	}

}
