package AUTO1.TestComponents;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AUTO1.resources.ExtentReporterNG;

public class Listeners1 extends BaseTest1 implements ITestListener {
	ExtentTest test ;
	ExtentReports extentRep=ExtentReporterNG.getReportObj();
	ThreadLocal <ExtentTest>threadloctest=new ThreadLocal<ExtentTest>();
	@SuppressWarnings("unchecked")
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		
		test=extentRep.createTest(result.getMethod().getMethodName());
		threadloctest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		ITestListener.super.onTestSuccess(result);
		threadloctest.get().log(Status.PASS, "Test is passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		threadloctest.get().fail(result.getThrowable());
		try {
			WebDriver driver1=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			String filepath=getScreenShot(result.getMethod().getMethodName(),driver1);
			threadloctest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extentRep.flush();
	}

}
