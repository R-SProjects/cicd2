package AUTO1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import AUTO1.Abstractcomponents.AbstractComponent1;

public class CheckOutPage1 extends AbstractComponent1{
	WebDriver driver;
	public CheckOutPage1(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css="input[placeholder='Select Country']") 
	WebElement selectedcountry;
	@FindBy (css="section.ta-results button")
	List<WebElement> countryList;

			
	By btn_submit= By.cssSelector(".action__submit");
	By countryresults=By.cssSelector("section.ta-results button");
	public void doCheckOut(String country ) {
		System.out.println(driver.getCurrentUrl());
		String c = (String.valueOf(country.charAt(3)));
		selectedcountry.sendKeys(country.split(c)[0]);
		
		waitForElementsToAppearByLoc(countryresults);
		for (WebElement coun : countryList) {
			if (coun.getText().equalsIgnoreCase(country)) {
				System.out.println(coun.getText());
				coun.click();
				break;

			}
			System.out.println("Cecked out for country sel" + coun.getText());
		}
		waitForElementToBeClicableByLoc(btn_submit);
	}
	public ConfirmationPage1 submitOrder() {
		waitForElementToAppearByLoc(btn_submit);
		driver.findElement(btn_submit).click();
		return new ConfirmationPage1(driver);
	}
	
}
