package AUTO1.Abstractcomponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage1 extends AbstractComponent1 {
	WebDriver driver;

	public OrdersPage1(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> products;

	public boolean verifyProductsOrder(String productsordered) {
		boolean match = products.stream()
				.anyMatch(selectedItem -> selectedItem.getText().equalsIgnoreCase(productsordered));
		System.out.println(products.get(0).getText());

		return match;
	}

}
