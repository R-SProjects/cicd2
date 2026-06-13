package AUTO1.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import AUTO1.TestComponents.BaseTest1;
import AUTO1.pageobjects.CartPage1;
import AUTO1.pageobjects.CheckOutPage1;
import AUTO1.pageobjects.ConfirmationPage1;
import AUTO1.pageobjects.LandingPage;
import AUTO1.pageobjects.ProductCat;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SubmitOrderStepDefinitions extends BaseTest1{
	public LandingPage landingpage;
	public ProductCat prodcat;
	public CartPage1 cartpage1;
	ConfirmationPage1 confirmpage;
	@Given ("The user lands on Ecom landing page")
	public void the_user_lands_on_Ecom_landing_page() throws IOException {
		landingpage=launchApplication();
	}
	@Given ("^The user logs in   with user  (.+) and password (.+)$")
	public void the_user_logs_in_with_user_password(String uname,String password) {
		 prodcat = landingpage.loginApplication(uname, password);
	}
	@When ("^The user adds the (.+) to the cart on page$")
	public void the_use_adds_the_product_to_the_cart(String product) {
		// prodcat.getProdName(productToAdd);
		prodcat.addToCart(product);
	}
	@And ("^Checksout (.+) and submit the order$")
	public void Checksout_and_submit_the_order(String product) {
		cartpage1=prodcat.goToCart();
		boolean match = cartpage1.verifyProductOnCartPage(product);
		Assert.assertTrue(match);
		CheckOutPage1 checkoutpage1 = cartpage1.goToCheckout();
		String country = "India";
		checkoutpage1.doCheckOut("India");
		confirmpage = checkoutpage1.submitOrder();
	}
	@Then ("Verify that the {string} message is displayed")
	public void Verify_that_the_message_is_displayed(String string) {
		//String orderConfmessageexpect = "Thankyou for the order.";
		Assert.assertEquals(confirmpage.getConfirmation(), string.toUpperCase());
		System.out.println("Successfull from framework structure ");
	}
	
		
}
