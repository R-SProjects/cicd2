package AUTO1.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AUTO1.Abstractcomponents.AbstractComponent1;

public class ConfirmationPage1 extends AbstractComponent1{
WebDriver driver;
public ConfirmationPage1(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);
}
@FindBy(className="hero-primary")
WebElement txt_orderConfrim;
public String getConfirmation() {
	return txt_orderConfrim.getText();
	
}
}
