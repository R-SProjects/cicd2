package AUTO1.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AUTO1.Abstractcomponents.AbstractComponent1;

public class ProductCat extends AbstractComponent1{
	WebDriver driver;
	public ProductCat(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//div[@class='card']//h5")
	List<WebElement> products;

	@FindBy(xpath="//ngx-spinner")
	WebElement spinner;
	@FindBy(css=".toast-bottom-right")
	WebElement toastmsg;
	public List<WebElement> getProductsList() {
		return products;
	}
	public String getProdName(String productname) {
		String prod = null;
		for (int i = 0; i < products.size(); i++) {
			
			if (getProductsList().get(i).getText().equalsIgnoreCase(productname)) {
				 prod="//div//div[@class='card-body']//h5/b[contains (text(),'" + productname
						+ "')]/ancestor::div";
				break;
				
			}
		}
		return prod;
		
	}
		public void  addToCart(String prod) {
				String cartlink=getProdName(prod);
				WebElement ele=driver.findElement(By.xpath(cartlink+"/button[text()=' Add To Cart']"));
				ele.click();
				waitForElementToDisappearWebElement(spinner);
				System.out.println(toastmsg.isDisplayed());
				System.out.println(toastmsg.getText());
				waitForElementToDisappearWebElement(toastmsg);
				System.out.println("Till Product func");
				
			}
		
		public void checkOut() {
			
		}
				
	}

