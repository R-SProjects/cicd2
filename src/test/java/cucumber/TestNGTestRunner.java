package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@CucumberOptions(features="\\src\\test\\java\\cucumber\\SubmitOrder.feature",glue="AUTO1\\StepDefinitions",monochrome=true,plugin={"html:\\LatestRep\\cucumber.html"})
@CucumberOptions(features="src/test/java/cucumber",glue="AUTO1/StepDefinitions",monochrome=true,tags="@Error",plugin= {"html:LatestRep/cucumberCC.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
