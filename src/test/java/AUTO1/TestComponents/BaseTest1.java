package AUTO1.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AUTO1.pageobjects.LandingPage;

public class BaseTest1 {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\AUTO1\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataTohashMap(String filepath) throws IOException {
		// convert json content to string
		
		String jsoncontent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		// create hashmap list out of it using jakson databind utility dependency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> testdata = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return testdata;

	}
	public String getScreenShot(String testcasename,WebDriver driver1) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver1;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, destFile);
		System.out.println("Test Failure Screen shot");
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
		}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goToUrl();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}