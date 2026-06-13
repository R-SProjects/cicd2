package AUTO1.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import AUTO1.TestComponents.BaseTest1;
import AUTO1.pageobjects.LandingPage;
import AUTO1.pageobjects.ProductCat;
import io.cucumber.java.en.Then;

@SuppressWarnings("unused")
public class ErrorValidationStepDefinitins extends BaseTest1{
	public LandingPage landingpage;
	ProductCat prodcat;
@Then("^Verify that the login error  message is displayed for (.+) and (.+)$")
public void verify_that_the_login_error_message_is_displayed(String uname ,String password) throws IOException {
	landingpage=launchApplication();
	  prodcat = landingpage.loginApplication(uname, password);
	
	Assert.assertEquals(landingpage.getErrorMessage(),"Incorrect email or password.");
	System.out.println("ErroHandling Test Passed");
	driver.quit();
}
}